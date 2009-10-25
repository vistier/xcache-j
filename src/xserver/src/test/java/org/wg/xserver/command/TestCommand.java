package org.wg.xserver.command;

import java.util.Date;

import org.wg.xserver.context.Context;

/**
 * 测试命令
 * @author enychen Oct 12, 2009
 */
public class TestCommand implements Command {

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.command.Command#execute(org.wg.xserver.command.CommandMessage,
     *      org.wg.xserver.Context)
     */
    public void execute(CommandMessage commandMessage, Context context) {
        TestRequest testRequest = new TestRequest(commandMessage);
        String out = (new Date()) + "测试命令，length=" + testRequest.getLength() + ", id=" + testRequest.getId()
                + ", commandId=" + testRequest.getCommandId() + ", test=" + testRequest.getTest()
                + ".";
        System.out.println(out);
    }
}
