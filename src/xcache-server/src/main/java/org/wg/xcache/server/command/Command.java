package org.wg.xcache.server.command;

import org.wg.xcache.server.context.Context;

/**
 * 命令
 * @author enychen Sep 6, 2009
 */
public interface Command {

    /**
     * 执行命令
     * @param context 上下文
     */
    void execute(Context context);

}
