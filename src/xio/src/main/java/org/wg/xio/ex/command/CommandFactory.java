package org.wg.xio.ex.command;

/**
 * 命令工厂
 * @author enychen Oct 11, 2009
 */
public interface CommandFactory {

    /**
     * 获取命令
     * @param commandMessage 命令消息
     * @return 命令
     */
    Command getCommand(CommandMessage commandMessage);

}
