package org.wg.xserver.command;

import org.wg.xserver.command.Command;
import org.wg.xserver.command.CommandFactory;
import org.wg.xserver.command.CommandMessage;

/**
 * 测试命令工厂
 * @author enychen Oct 12, 2009
 */
public class TestCommandFactory implements CommandFactory {

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.command.CommandFactory#getCommand(org.wg.xserver.command.CommandMessage)
     */
    public Command getCommand(CommandMessage commandMessage) {
        return new TestCommand();
    }

}
