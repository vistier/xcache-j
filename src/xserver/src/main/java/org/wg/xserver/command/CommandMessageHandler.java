package org.wg.xserver.command;

import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xserver.MessageHandler;
import org.wg.xserver.context.Context;

/**
 * 命令消息处理器
 * @author enychen Oct 11, 2009
 */
public class CommandMessageHandler implements MessageHandler {

    /** log */
    private static final Log log = LogFactory.getLog(CommandMessageHandler.class);

    /** 命令工厂 */
    private CommandFactory   commandFactory;

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.MessageHandler#handle(java.nio.ByteBuffer,
     *      org.wg.xserver.Context)
     */
    public void handle(ByteBuffer message, Context context) {
        try {
//            byte[] bt = new byte[message.limit()];
//            String msg = "";
//            message.get(bt);
//            for (byte b : bt) {
//                msg += b + " ";
//            }
//            log.info("message: " + msg);
//
//            ByteBuffer receivedMessage;
//
//            if (context.getReceivedMessageBuffer() == null) {
//                receivedMessage = ByteBuffer.allocate(message.limit());
//                //message.flip();
//                receivedMessage.put(message);
//            } else {
//                ByteBuffer temp1 = context.getReceivedMessageBuffer();
//                receivedMessage = ByteBuffer.allocate(temp1.limit() + message.limit());
//                temp1.flip(); message.flip();
//                receivedMessage.put(temp1).put(message);
//            }
//
//            context.setReceivedMessageBuffer(receivedMessage);
//
//            bt = new byte[receivedMessage.limit()];
//            receivedMessage.flip();
//            receivedMessage.get(bt);
//            msg = "";
//            for (byte b : bt) {
//                msg += b + " ";
//            }
//            log.info("receivedMessage: " + msg);
//            //receivedMessage.compact();
//            
//            // context.receive(message);
//            receivedMessage.flip();
            context.getSocketChannel().write(context.getMessageByLength(3));
            //context.getReceivedMessageBuffer().flip();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // context.resumeSelectWrite();
        }
    }

    /**
     * 设置命令工厂
     * @param commandFactory 命令工厂
     */
    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

}
