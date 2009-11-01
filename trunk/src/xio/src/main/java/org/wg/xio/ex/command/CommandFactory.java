package org.wg.xio.ex.command;

/**
 * 命令工厂
 * @author enychen Oct 11, 2009
 */
public interface CommandFactory {

    /**
     * 获取命令
     * @param commandRequest 命令请求
     * @return 命令
     */
    Command getCommand(CommandRequest commandRequest);

}
