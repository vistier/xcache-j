package com.wg.xserver.command;

/**
 * @author enychen Oct 12, 2009
 */
public class DefaultCommandFactory implements CommandFactory {

    /*
     * (non-Javadoc)
     * @see com.wg.xserver.command.CommandFactory#getCommand(com.wg.xserver.command.CommandMessage)
     */
    public Command getCommand(CommandMessage commandMessage) {
        return new DefaultCommand();
    }

}
