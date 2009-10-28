package org.wg.xio;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xio.config.Supporter;

/**
 * Xserver
 * <p>
 * 一个可扩展的服务器框架
 * @author enychen Oct 11, 2009
 */
public class Xserver {

    /** log */
    private static final Log      log = LogFactory.getLog(Xserver.class);

    /** 支持者 */
    protected Supporter           supporter;

    /** 服务器socket通道 */
    protected ServerSocketChannel serverSocketChannel;

    /** 端口 */
    protected int                 port;

    /** Socket处理器 */
    protected SocketHandler[]     socketHandlers;

    /** socket处理器数量 */
    protected int                 socketHandlerCount;

    /** 接收器 */
    protected Acceptor            acceptor;

    /** 已接收的次数 */
    protected int                 acceptedTimes;

    /**
     * 创建Xserver
     * @param supporter 支持者
     */
    public Xserver(Supporter supporter) {
        this.supporter = supporter;
        this.port = supporter.getConfig().getPort();
        this.socketHandlerCount = supporter.getConfig().getSocketHandlerCount();
        this.socketHandlers = new SocketHandler[this.socketHandlerCount];

        for (int i = 0; i < this.socketHandlerCount; i++) {
            this.socketHandlers[i] = new SocketHandler(supporter);
        }
    }

    /**
     * 启动服务器
     */
    public void start() {
        if (this.acceptor == null) {
            // --启动接收器线程
            this.acceptor = new Acceptor();
            this.supporter.getExecutor().execute(acceptor);
        }
    }

    /**
     * 接收器
     * @author enychen Oct 13, 2009
     */
    private class Acceptor implements Runnable {

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        public void run() {
            try {
                // --服务器启动
                serverSocketChannel = ServerSocketChannel.open();
                InetSocketAddress address = new InetSocketAddress(port);
                serverSocketChannel.socket().bind(address);
            } catch (Exception e) {
                log.error("服务器启动异常！", e);

                // --服务器关闭
                if (serverSocketChannel != null) {
                    try {
                        serverSocketChannel.close();
                    } catch (Exception e1) {
                        log.error("服务器关闭异常！", e1);
                    }
                }

                return;
            }

            if (log.isInfoEnabled()) {
                log.info("xserver启动！");
            }
            
            while (supporter.isRunning()) {
                try {
                    // 等待接收连接
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    if (log.isInfoEnabled()) {
                        log.info("接收到来自" + socketChannel.socket().getRemoteSocketAddress()
                                 + "的连接。");
                    }

                    // 对socket负载均衡处理
                    socketHandlers[acceptedTimes++ % socketHandlerCount].bind(socketChannel);
                } catch (Exception e) {
                    log.error("连接异常！", e);
                }
            }
        }
    }

}
