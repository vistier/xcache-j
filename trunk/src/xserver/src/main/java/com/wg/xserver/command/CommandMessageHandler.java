package com.wg.xserver.command;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.wg.xserver.MessageHandler;
import com.wg.xserver.context.Context;

/**
 * 命令消息处理器
 * @author enychen Oct 11, 2009
 */
public class CommandMessageHandler implements MessageHandler {

    /** 命令工厂 */
    private CommandFactory commandFactory;

    /*
     * (non-Javadoc)
     * @see com.wg.xserver.MessageHandler#getMessage(com.wg.xserver.Context)
     */
    public ByteBuffer getMessage(Context context) {
        ByteBuffer message = ByteBuffer.allocate(5);
        
        try {
            context.getSocketChannel().read(message);
            message.flip();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return message;
    }

    /*
     * (non-Javadoc)
     * @see com.wg.xserver.MessageHandler#handle(java.nio.ByteBuffer,
     *      com.wg.xserver.Context)
     */
    public void handle(ByteBuffer message, Context context) {
        CommandMessage commandMessage = new CommandMessage(message);
        Command command = this.commandFactory.getCommand(commandMessage);
        command.execute(commandMessage, context);
    }

    /**
     * 设置命令工厂
     * @param commandFactory 命令工厂
     */
    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

}
