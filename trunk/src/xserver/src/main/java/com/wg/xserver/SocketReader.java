package com.wg.xserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.wg.xserver.context.Context;

/**
 * socket读取器
 * @author enychen Oct 14, 2009
 */
public class SocketReader implements Runnable {

    /** 上下文 */
    private Context        context;

    /**  */
    private SocketChannel  socketChannel;

    /** 消息处理器 */
    private MessageHandler messageHandler;

    /** 缓冲区大小 */
    private int            bufferSize;

    /**
     * 创建socket读取器
     * @param context 上下文
     */
    public SocketReader(Context context) {
        this.context = context;
        this.socketChannel = context.getSocketChannel();
        this.messageHandler = context.getServerSupporter().getMessageHandler();
        this.bufferSize = context.getServerSupporter().getServerConfig().getBufferSize();
    }

    /*
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            ByteBuffer message = ByteBuffer.allocateDirect(this.bufferSize);

            while (this.socketChannel.read(message) > 0) {
                message.flip();

                this.messageHandler.handle(message, context);

                message.clear();
            }
        } catch (IOException e) {
            // TODO log
            e.printStackTrace();
        } finally {
            context.resumeSelectRead();
            //context.getKey().selector().wakeup();
        }

    }
}
