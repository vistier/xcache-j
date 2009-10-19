package org.wg.xserver;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xserver.context.Context;

/**
 * socket读取器
 * @author enychen Oct 14, 2009
 */
public class SocketReader implements Runnable {

    /** log */
    private static final Log log = LogFactory.getLog(SocketReader.class);

    /** 上下文 */
    private Context          context;

    /**  */
    private SocketChannel    socketChannel;

    /** 消息处理器 */
    private MessageHandler   messageHandler;

    /** 缓冲区大小 */
    private int              bufferSize;

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
            ByteBuffer message = ByteBuffer.allocate(this.bufferSize);

            while (this.socketChannel.read(message) > 0) {
                message.flip();

                context.receive(message);
                
                this.messageHandler.handle(context);

                message.clear();
            }
        } catch (Exception e) {
            log.error("socket读取异常！", e);
        } finally {
            context.resumeSelectRead();
        }
    }

}
