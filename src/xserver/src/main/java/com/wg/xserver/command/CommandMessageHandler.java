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
     * @see com.wg.xserver.MessageHandler#handle(java.nio.ByteBuffer,
     *      com.wg.xserver.Context)
     */
    public void handle(ByteBuffer message, Context context) {
        try {
            message.flip();
            context.getSocketChannel().write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
