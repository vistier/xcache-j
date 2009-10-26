package org.wg.xio.command;

import org.wg.xio.context.Context;

/**
 * 测试命令
 * @author enychen Oct 12, 2009
 */
public class TestCommand implements Command {

    /*
     * (non-Javadoc)
     * @see org.wg.xio.command.Command#execute(org.wg.xio.command.CommandMessage,
     *      org.wg.xio.Context)
     */
    public void execute(CommandMessage commandMessage, Context context) {
        TestRequest testRequest = new TestRequest(commandMessage);
        testRequest.decode(commandMessage.getMessage());

        String out = context.getHostAddress() + "-测试命令，length=" + testRequest.getLength()
                + ", id=" + testRequest.getId() + ", commandId=" + testRequest.getCommandId()
                + ", test=" + testRequest.getTest();
        System.out.println(out);
    }
}
