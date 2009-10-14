package com.wg.xserver;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.wg.xserver.context.Context;

/**
 * socket读取器
 * @author enychen Oct 14, 2009
 */
public class SocketReader implements Runnable {

    /** 上下文 */
    private Context        context;

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
        this.messageHandler = context.getServerSupporter().getMessageHandler();
        this.bufferSize = context.getServerSupporter().getServerConfig().getBufferSize();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            ByteBuffer message = ByteBuffer.allocateDirect(this.bufferSize);

            while (this.context.getSocketChannel().read(message) > 0) {
                this.messageHandler.handle(message, context);
            }
        } catch (IOException e) {
            // TODO log
        } finally {
            // context.getKey().interestOps(SelectionKey.OP_READ);
            // context.getKey().selector().wakeup();
        }

    }
}
