package org.wg.xio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xio.context.Context;

/**
 * socket读取器
 * @author enychen Oct 14, 2009
 */
public class SocketReader implements Runnable {

    /** log */
    private static final Log log = LogFactory.getLog(SocketReader.class);

    /** 上下文 */
    protected Context        context;

    /**  */
    protected SocketChannel  socketChannel;

    /** 消息处理器 */
    protected MessageHandler messageHandler;

    /** 缓冲区大小 */
    protected int            bufferSize;

    /**
     * 创建socket读取器
     * @param context 上下文
     */
    public SocketReader(Context context) {
        this.context = context;
        this.socketChannel = context.getSocketChannel();
        this.messageHandler = context.getSupporter().getMessageHandler();
        this.bufferSize = context.getSupporter().getConfig().getBufferSize();
    }

    /*
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            ByteBuffer message = ByteBuffer.allocate(this.bufferSize);
            int readLength;

            // --读取消息并处理
            while ((readLength = this.socketChannel.read(message)) > 0) {
                message.flip();

                this.context.receive(message);

                if (this.messageHandler != null) {
                    this.messageHandler.handle(context);
                }

                message.clear();
            }

            // --关闭连接
            if (readLength < 0) {
                this.context.close();
            }
        } catch (Exception e) {
            log.error("socket读取异常！", e);

            // --IO异常，关闭连接
            if (e instanceof IOException) {
                this.context.close();
            }
        } finally {
            // --继续选择读取
            if (this.context.getSocketChannel().isOpen()) {
                context.resumeSelectRead();
            }
        }
    }

}
