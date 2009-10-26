package org.wg.xio.command;

import org.wg.xio.command.Command;
import org.wg.xio.command.CommandFactory;
import org.wg.xio.command.CommandMessage;

/**
 * 测试命令工厂
 * @author enychen Oct 12, 2009
 */
public class TestCommandFactory implements CommandFactory {

    /*
     * (non-Javadoc)
     * @see org.wg.xio.command.CommandFactory#getCommand(org.wg.xio.command.CommandMessage)
     */
    public Command getCommand(CommandMessage commandMessage) {
        return new TestCommand();
    }

}
