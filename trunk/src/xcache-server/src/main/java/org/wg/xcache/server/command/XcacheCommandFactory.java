package org.wg.xcache.server.command;

import java.util.Map;

import org.wg.xio.ex.command.Command;
import org.wg.xio.ex.command.CommandFactory;
import org.wg.xio.ex.command.CommandRequest;

/**
 * Xcahce命令工厂
 * @author enychen Nov 3, 2009
 */
public class XcacheCommandFactory implements CommandFactory {

    /** 命令Map，key是commandId，value是command */
    private Map<String, Command> commandMap;

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandFactory#getCommand(org.wg.xio.ex.command.CommandRequest)
     */
    public Command getCommand(CommandRequest commandRequest) {
        return this.commandMap.get(String.valueOf(commandRequest.getCommandId()));
    }

    // --Bean注入

    /**
     * 设置命令Map，key是commandId，value是command
     * @param commandMap 命令Map
     */
    public void setCommandMap(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

}
