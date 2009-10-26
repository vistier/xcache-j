package org.wg.xio;

import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xio.context.Context;

/**
 * socket写入器
 * @author enychen Oct 18, 2009
 */
public class SocketWriter implements Runnable {

    /** log */
    private static final Log log = LogFactory.getLog(SocketWriter.class);

    /** 上下文 */
    protected Context        context;

    /**
     * 创建socket写入器
     * @param context 上下文
     */
    public SocketWriter(Context context) {
        this.context = context;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            ByteBuffer message;

            while ((message = this.context.getSendingMessageQueue().poll()) != null) {
                while (message.hasRemaining()) {
                    context.getSocketChannel().write(message);
                }
            }
        } catch (Exception e) {
            log.error("socket写入异常！", e);
        } finally {
            context.writeFinished();
        }
    }

}
