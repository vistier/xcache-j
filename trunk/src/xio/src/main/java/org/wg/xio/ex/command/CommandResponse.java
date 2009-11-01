package org.wg.xio.ex.command;

import java.nio.ByteBuffer;

import org.wg.xio.Message;
import org.wg.xio.util.XioConst;

/**
 * 命令响应
 * @author enychen Oct 31, 2009
 */
public class CommandResponse extends Message {

    /** 消息长度 */
    protected int length;

    /** 消息ID */
    protected int id;

    /*
     * (non-Javadoc)
     * @see org.wg.xio.Message#encode()
     */
    @Override
    public ByteBuffer encode() {
        ByteBuffer message = ByteBuffer.allocate(XioConst.COMMAND_RESPONSE_HEADER_LENGTH);
        message.putInt(XioConst.LENGTH_LENGTH);
        message.putInt(this.id);
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
        this.id = message.getInt();
    }

    /**
     * 获取消息长度
     * @return 消息长度
     */
    public int getLength() {
        return length;
    }

    /**
     * 获取消息ID
     * @return 消息ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置消息ID
     * @param id 消息ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 拷贝命令响应
     * @param commandResponse 命令响应
     */
    public void copy(CommandResponse commandResponse) {
        this.length = commandResponse.length;
        this.id = commandResponse.id;
        this.message = commandResponse.message;
    }

}
