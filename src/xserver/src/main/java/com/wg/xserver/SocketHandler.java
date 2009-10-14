package com.wg.xserver;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.wg.xserver.context.Context;
import com.wg.xserver.context.ServerSupporter;

/**
 * Socket处理器
 * @author enychen Sep 6, 2009
 */
public class SocketHandler {

    /** 服务器支持者 */
    private ServerSupporter            serverSupporter;

    /** 选择器 */
    private Selector                   selector;

    /** 处理器 */
    private Handler                    handler;

    /** socket通道队列 */
    private Queue<SocketChannel>       socketChannelQueue     = new ConcurrentLinkedQueue<SocketChannel>();

    /** 上下文关联的socket读取器Map */
    private Map<Context, SocketReader> contextSocketReaderMap = new ConcurrentHashMap<Context, SocketReader>();

    /**
     * 创建Socket处理器
     * @param serverSupporter 服务器支持者
     */
    public SocketHandler(ServerSupporter serverSupporter) {
        this.serverSupporter = serverSupporter;
    }

    // ------------------------------------------------------------
    // 处理器工作流程：
    // 1、绑定socket通道；
    // 2、启动处理线程；
    // 3、处理前准备；
    // 4、处理，或者读取，或者回写。
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

                this.handler = new Handler();
                this.serverSupporter.getExecutor().execute(this.handler);
            }

            this.selector.wakeup();
        } catch (Exception e) {
            // TODO log
        }
    }

    /**
     * 处理前准备
     */
    protected void prepare() {
        SocketChannel socketChannel;

        while ((socketChannel = this.socketChannelQueue.poll()) != null) {
            try {
                Context context = new Context();
                context.setSocketChannel(socketChannel);
                context.setServerSupporter(this.serverSupporter);

                socketChannel.configureBlocking(false);
                context.setKey(socketChannel.register(selector, SelectionKey.OP_READ, context));

                SocketReader socketReader = new SocketReader(context);
                this.contextSocketReaderMap.put(context, socketReader);
            } catch (Exception e) {
                // TODO log
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
    }

    /**
     * 读取
     * @param context 上下文
     */
    protected void read(Context context) {
        context.getKey().interestOps(0);

        SocketReader socketReader = this.contextSocketReaderMap.get(context);
        this.serverSupporter.getExecutor().execute(socketReader);
    }

    /**
     * 回写
     * @param context 上下文
     */
    protected void write(Context context) {

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
            try {
                while (serverSupporter.isRunning()) {
                    selector.select();

                    prepare();

                    Set<SelectionKey> keys = selector.selectedKeys();

                    handle(keys);

                    keys.clear();
                }
            } catch (Exception e) {
                // TODO log
            }
        }
    }

}
