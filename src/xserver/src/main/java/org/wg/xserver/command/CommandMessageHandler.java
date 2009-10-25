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
     * @see org.wg.xserver.MessageHandler#handle(org.wg.xserver.Context)
     */
    public void handle(Context context) {
        try {
            while (context.getReceivedMessageBuffer().limit() >= 4) {
                // --获取一个长度为length的消息
                int length = context.getReceivedMessageBuffer().getInt();
                ByteBuffer message = context.getMessageByLength(length);

                if (message == null) {
                    break;
                }

                // --执行命令
                CommandMessage commandMessage = new CommandMessage();
                commandMessage.decode(message);
                Command command = this.commandFactory.getCommand(commandMessage);
                command.execute(commandMessage, context);
            }
        } catch (Exception e) {
            log.error("命令消息处理异常！", e);
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
