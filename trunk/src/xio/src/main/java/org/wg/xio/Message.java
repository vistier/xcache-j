package org.wg.xio;

import java.nio.ByteBuffer;

/**
 * 消息
 * @author enychen Oct 19, 2009
 */
public abstract class Message {

    /** 消息 */
    protected ByteBuffer message;

    /**
     * 获取消息
     * @return 消息
     */
    public ByteBuffer getMessage() {
        return message;
    }

    /**
     * 编码
     * @return 消息
     */
    public abstract ByteBuffer encode();

    /**
     * 解码
     * @param message 消息
     */
    public abstract void decode(ByteBuffer message);

}
