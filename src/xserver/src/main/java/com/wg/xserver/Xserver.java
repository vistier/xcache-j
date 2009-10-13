package com.wg.xserver;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.wg.xserver.context.ServerSupporter;

/**
 * Xserver
 * <p>
 * 一个可扩展的服务器框架
 * @author enychen Oct 11, 2009
 */
public class Xserver {

    /** 服务器支持者 */
    private ServerSupporter serverSupporter;

    /** Socket处理器 */
    private SocketHandler[] socketHandlers;

    /** socket处理器数量 */
    private int             socketHandlerCount;

    /** 接收器 */
    private Acceptor        acceptor;

    /** 已接收的次数 */
    private int             acceptedTimes;

    /**
     * 创建Xserver
     * @param serverSupporter 服务器支持者
     */
    public Xserver(ServerSupporter serverSupporter) {
        this.serverSupporter = serverSupporter;

        this.socketHandlerCount = serverSupporter.getServerConfig().getSocketHandlerCount();
        this.socketHandlers = new SocketHandler[this.socketHandlerCount];

        for (int i = 0; i < this.socketHandlerCount; i++) {
            this.socketHandlers[i] = new SocketHandler(serverSupporter);
        }
    }

    /**
     * 启动服务器
     */
    public void start() {
        if (this.acceptor == null) {
            this.acceptor = new Acceptor();
            this.serverSupporter.getExecutor().execute(acceptor);
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
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                InetSocketAddress address = new InetSocketAddress(serverSupporter.getServerConfig()
                        .getPort());
                serverSocketChannel.socket().bind(address);

                while (serverSupporter.isRunning()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    // 对socket负载均衡处理
                    socketHandlers[acceptedTimes++ % socketHandlerCount].bind(socketChannel);
                }
            } catch (Exception e) {
                // TODO log
            }
        }
    }

}
