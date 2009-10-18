package org.wg.xserver.command;

import java.nio.ByteBuffer;

import org.wg.xserver.MessageHandler;
import org.wg.xserver.context.Context;

/**
 * 命令消息处理器
 * @author enychen Oct 11, 2009
 */
public class CommandMessageHandler implements MessageHandler {

    /** 命令工厂 */
    private CommandFactory commandFactory;

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.MessageHandler#handle(java.nio.ByteBuffer,
     *      org.wg.xserver.Context)
     */
    public void handle(ByteBuffer message, Context context) {
        try {
            //Thread.sleep(100);
            //context.send(message);
            context.getSocketChannel().write(message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //context.resumeSelectWrite();
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
