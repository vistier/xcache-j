package com.wg.xserver;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.wg.xserver.context.ServerSupporter;

/**
 * Socket处理器
 * @author enychen Sep 6, 2009
 */
public class SocketHandler {

    /** 服务器支持者 */
    private ServerSupporter      serverSupporter;

    /** 选择器 */
    private Selector             selector;

    /** 处理器 */
    private Handler              handler;

    /** socket通道队列 */
    private Queue<SocketChannel> socketChannelQueue = new ConcurrentLinkedQueue<SocketChannel>(); ;

    /**
     * 创建Socket处理器
     * @param serverSupporter 服务器支持者
     */
    public SocketHandler(ServerSupporter serverSupporter) {
        this.serverSupporter = serverSupporter;
    }

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
     * 处理准备
     */
    private void prepare() {
        SocketChannel socketChannel;

        while ((socketChannel = this.socketChannelQueue.poll()) != null) {
            try {
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } catch (Exception e) {
                // TODO log
            }
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
            try {
                while (serverSupporter.isRunning()) {
                    selector.select();

                    prepare();

                    Set<SelectionKey> keys = selector.selectedKeys();

                    for (SelectionKey key : keys) {
                        if (key.isReadable()) {
                            System.out.println("raad");
                        } else if (key.isWritable()) {
                            System.out.println("write");
                        }
                    }
                    
                    keys.clear();
                }
            } catch (Exception e) {
                // TODO log
            }
        }

    }

}
