package com.wg.xserver;

import java.nio.ByteBuffer;

import com.wg.xserver.context.Context;

/**
 * 消息处理器
 * @author enychen Oct 11, 2009
 */
public interface MessageHandler {

    /**
     * 获取消息
     * @param context 上下文
     * @return 消息
     */
    ByteBuffer getMessage(Context context);

    /**
     * 处理消息
     * @param message 消息
     * @param context 上下文
     */
    void handle(ByteBuffer message, Context context);

}
