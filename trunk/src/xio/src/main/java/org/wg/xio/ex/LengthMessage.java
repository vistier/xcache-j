package org.wg.xio.ex;

import java.nio.ByteBuffer;

import org.wg.xio.Message;
import org.wg.xio.util.XioConst;

/**
 * 长度消息
 * @author enychen Oct 28, 2009
 */
public class LengthMessage extends Message {

    /** 消息长度 */
    protected int        length;

    /** 消息体 */
    protected ByteBuffer body;

    /*
     * (non-Javadoc)
     * @see org.wg.xio.Message#encode()
     */
    @Override
    public ByteBuffer encode() {
        this.length = XioConst.LENGTH_LENGTH + this.body.limit();

        ByteBuffer message = ByteBuffer.allocate(this.length);
        message.putInt(this.length);
        message.put(this.body);
        message.flip();

        return message;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.Message#decode(java.nio.ByteBuffer)
     */
    @Override
    public void decode(ByteBuffer message) {
        this.message = message;
        this.length = message.getInt();
        this.body = ByteBuffer.allocate(message.remaining());
        this.body.put(message);
        this.body.flip();
    }

    /**
     * 获取消息长度
     * @return 消息长度
     */
    public int getLength() {
        return length;
    }

    /**
     * 获取消息体
     * @return 消息体
     */
    public ByteBuffer getBody() {
        return body;
    }

    /**
     * 设置消息体
     * @param body 消息体
     */
    public void setBody(ByteBuffer body) {
        this.body = body;
    }

}
