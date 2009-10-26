package org.wg.xio;

import org.wg.xio.context.Context;

/**
 * 消息处理器
 * @author enychen Oct 11, 2009
 */
public interface MessageHandler {

    /**
     * 处理消息
     * @param context 上下文
     */
    void handle(Context context);

}
