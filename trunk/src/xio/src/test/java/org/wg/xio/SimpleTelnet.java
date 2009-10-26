package org.wg.xio;

import java.nio.ByteBuffer;

import org.wg.xio.context.Context;

/**
 * 简单的telnet
 * @author enychen Oct 26, 2009
 */
public class SimpleTelnet implements MessageHandler {

    /*
     * (non-Javadoc)
     * @see org.wg.xio.MessageHandler#handle(org.wg.xio.context.Context)
     */
    public void handle(Context context) {
        ByteBuffer message = context.getMessageByLength(context.getReceivedMessageBuffer().limit());
        context.write(message);
    }

}
