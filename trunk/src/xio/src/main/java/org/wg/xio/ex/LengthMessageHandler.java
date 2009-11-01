package org.wg.xio.ex;

import java.nio.ByteBuffer;

import org.wg.xio.MessageHandler;
import org.wg.xio.context.Context;
import org.wg.xio.util.XioConst;

/**
 * 长度消息处理器
 * @author enychen Oct 28, 2009
 */
public class LengthMessageHandler implements MessageHandler {

    /*
     * (non-Javadoc)
     * @see org.wg.xio.MessageHandler#handle(org.wg.xio.context.Context)
     */
    public void handle(Context context) {
        while (context.getReceivedMessageBuffer().limit() >= XioConst.LENGTH_LENGTH) {
            // --获取一个长度为length的消息
            int length = context.getReceivedMessageBuffer().getInt();
            ByteBuffer message = context.getMessageByLength(length);

            if (message == null) {
                break;
            }

            synchronized (context.getReadLock()) {
                // context.getReceivedMessageQueue().clear();

                // --放入队列，并通知可以读取了
                context.getReceivedMessageQueue().add(message);

                context.getReadLock().notify();
            }
        }
    }

}
