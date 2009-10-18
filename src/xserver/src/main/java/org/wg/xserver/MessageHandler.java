package org.wg.xserver;

import java.nio.ByteBuffer;

import org.wg.xserver.context.Context;


/**
 * 消息处理器
 * @author enychen Oct 11, 2009
 */
public interface MessageHandler {

    /**
     * 处理消息
     * @param message 消息
     * @param context 上下文
     */
    void handle(ByteBuffer message, Context context);

}
