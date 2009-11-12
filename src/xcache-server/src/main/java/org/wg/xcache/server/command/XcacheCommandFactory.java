package org.wg.xcache.server.command;

import org.wg.xio.ex.command.Command;
import org.wg.xio.ex.command.CommandFactory;
import org.wg.xio.ex.command.CommandRequest;

/**
 * Xcahce命令工厂
 * @author enychen Nov 3, 2009
 */
public class XcacheCommandFactory implements CommandFactory {

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandFactory#getCommand(org.wg.xio.ex.command.CommandRequest)
     */
    public Command getCommand(CommandRequest commandRequest) {
        return new PutObject();
    }

}
