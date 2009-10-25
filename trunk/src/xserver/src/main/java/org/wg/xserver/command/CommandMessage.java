package org.wg.xserver.command;

import java.nio.ByteBuffer;

import org.wg.xserver.Message;

/**
 * 命令消息
 * @author enychen Oct 11, 2009
 */
public class CommandMessage extends Message {

    /** 消息长度 */
    protected int   length = 8;

    /** 消息ID */
    protected short id;

    /** 命令ID */
    protected short commandId;

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.Message#encode()
     */
    @Override
    public ByteBuffer encode() {
        ByteBuffer message = ByteBuffer.allocate(this.length);
        message.putInt(this.length);
        message.putShort(this.id);
        message.putShort(this.commandId);
        message.flip();

        return message;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.Message#decode(java.nio.ByteBuffer)
     */
    @Override
    public void decode(ByteBuffer message) {
        this.message = message;
        this.length = message.getInt();
        this.id = message.getShort();
        this.commandId = message.getShort();
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
    public void setId(short id) {
        this.id = id;
    }

    /**
     * 获取命令ID
     * @return 命令ID
     */
    public int getCommandId() {
        return commandId;
    }

    /**
     * 设置命令ID
     * @param commandId 命令ID
     */
    public void setCommandId(short commandId) {
        this.commandId = commandId;
    }

    /**
     * 拷贝命令消息
     * @param commandMessage 命令消息
     */
    public void copy(CommandMessage commandMessage) {
        this.length = commandMessage.length;
        this.id = commandMessage.id;
        this.commandId = commandMessage.commandId;
        this.message = commandMessage.message;
    }

}
