package org.wg.xio.ex.command;

import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xio.MessageHandler;
import org.wg.xio.context.Context;
import org.wg.xio.util.XioConst;

/**
 * 命令消息处理器
 * @author enychen Oct 11, 2009
 */
public class CommandHandler implements MessageHandler {

    /** log */
    private static final Log log = LogFactory.getLog(CommandHandler.class);

    /** 命令工厂 */
    protected CommandFactory commandFactory;

    /*
     * (non-Javadoc)
     * @see org.wg.xio.MessageHandler#handle(org.wg.xio.Context)
     */
    public void handle(Context context) {
        try {
            while (context.getReceivedMessageBuffer().limit() >= XioConst.LENGTH_LENGTH) {
                // --获取一个长度为length的消息
                int length = context.getReceivedMessageBuffer().getInt();
                ByteBuffer message = context.getMessageByLength(length);

                if (message == null) {
                    break;
                }

                // --执行命令
                CommandRequest commandRequest = new CommandRequest();
                commandRequest.decode(message);
                Command command = this.commandFactory.getCommand(commandRequest);
                command.execute(commandRequest, context);
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
