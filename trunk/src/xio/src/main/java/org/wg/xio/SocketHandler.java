package org.wg.xio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xio.config.Supporter;
import org.wg.xio.context.Context;

/**
 * Socket处理器
 * @author enychen Sep 6, 2009
 */
public class SocketHandler {

    /** log */
    private static final Log              log                    = LogFactory.getLog(SocketHandler.class);

    /** 支持者 */
    protected Supporter                   supporter;

    /** 选择器 */
    protected Selector                    selector;

    /** 处理器 */
    protected Handler                     handler;

    /** socket通道队列 */
    protected Queue<SocketChannel>        socketChannelQueue     = new ConcurrentLinkedQueue<SocketChannel>();

    /** socket通道关联的上下文 */
    protected Map<SocketChannel, Context> socketChannelContext   = new ConcurrentHashMap<SocketChannel, Context>();

    /** 上下文关联的socket读取器Map */
    protected Map<Context, SocketReader>  contextSocketReaderMap = new ConcurrentHashMap<Context, SocketReader>();

    /** 上下文关联的socket写入器Map */
    protected Map<Context, SocketWriter>  contextSocketWriterMap = new ConcurrentHashMap<Context, SocketWriter>();

    /**
     * 创建Socket处理器
     * @param supporter 支持者
     */
    public SocketHandler(Supporter supporter) {
        this.supporter = supporter;
    }

    // ------------------------------------------------------------
    // 处理器工作流程：
    // 1、绑定socket通道；
    // 2、启动处理线程；
    // 3、处理前准备；
    // 4、处理，或者读取，或者写入。
    // ------------------------------------------------------------

    /**
     * 绑定socket通道
     * @param socketChannel socket通道
     */
    public void bind(SocketChannel socketChannel) {
        try {
            this.socketChannelQueue.add(socketChannel);

            if (this.handler == null) {
                this.selector = Selector.open();

                // --启动处理器线程
                this.handler = new Handler();
                this.supporter.getExecutor().execute(this.handler);
            }

            this.selector.wakeup();
        } catch (Exception e) {
            log.error("绑定socket通道异常！", e);
        }
    }

    /**
     * 获取上下文
     * @param socketChannel socket通道
     * @return 上下文
     */
    public Context getContext(SocketChannel socketChannel) {
        return this.socketChannelContext.get(socketChannel);
    }
    
    /**
     * 处理前准备
     */
    protected void prepare() {
        SocketChannel socketChannel;

        while ((socketChannel = this.socketChannelQueue.poll()) != null) {
            try {
                // --初始化上下文
                Context context = new Context();
                context.setSocketChannel(socketChannel);
                context.setSupporter(this.supporter);
                context.setSocketHandler(this);

                socketChannel.configureBlocking(false);
                context.setKey(socketChannel.register(this.selector, SelectionKey.OP_READ, context));

                this.socketChannelContext.put(socketChannel, context);
                
                SocketReader socketReader = new SocketReader(context);
                this.contextSocketReaderMap.put(context, socketReader);

                SocketWriter socketWriter = new SocketWriter(context);
                this.contextSocketWriterMap.put(context, socketWriter);
            } catch (Exception e) {
                log.error("socket处理前准备异常！", e);
            }
        }
    }

    /**
     * 处理
     * @param keys 选择键
     */
    protected void handle(Set<SelectionKey> keys) {
        for (SelectionKey key : keys) {
            Context context = (Context) key.attachment();

            if (key.isReadable()) {
                this.read(context);
            }

            if (key.isWritable()) {
                this.write(context);
            }
        }

        keys.clear();
    }

    /**
     * 读取
     * @param context 上下文
     */
    protected void read(Context context) {
        // 读取时，要暂停选择读取
        context.suspendSelectRead();

        // --读取
        SocketReader socketReader = this.contextSocketReaderMap.get(context);

        if (this.supporter.getConfig().isSyncRead()) {
            socketReader.run();
        } else {
            this.supporter.getExecutor().execute(socketReader);
        }
    }

    /**
     * 写入
     * @param context 上下文
     */
    protected void write(Context context) {
        // 写入时，要暂停选择写入
        context.suspendSelectWrite();

        // --写入
        SocketWriter socketWriter = this.contextSocketWriterMap.get(context);

        if (this.supporter.getConfig().isSyncWrite()) {
            socketWriter.run();
        } else {
            this.supporter.getExecutor().execute(socketWriter);
        }
    }

    /**
     * 关闭连接
     * @param context 上下文
     */
    public void close(Context context) {
        try {
            context.getKey().cancel();
            context.getSocketChannel().close();

            this.socketChannelContext.remove(context.getSocketChannel());
            this.contextSocketReaderMap.remove(context);
            this.contextSocketWriterMap.remove(context);
        } catch (Exception e) {
            log.error("关闭与" + context.getHostAddress() + "的连接异常！", e);
        }
        
        if (log.isInfoEnabled()) {
            log.info("关闭与" + context.getHostAddress() + "的连接。");
        }
    }

    /**
     * 处理器
     * @author enychen Oct 13, 2009
     */
    private class Handler implements Runnable {

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        public void run() {

            while (supporter.isRunning()) {
                try {
                    // --选择器选择并处理
                    int keyCount = selector.select(1000);

                    prepare();

                    if (keyCount > 0) {
                        handle(selector.selectedKeys());
                    }
                } catch (Exception e) {
                    log.error("socket处理异常！", e);
                }
            }
        }
    }

}
