package org.wg.xserver.command;

import java.nio.ByteBuffer;

import org.wg.xserver.Message;

/**
 * 命令消息
 * @author enychen Oct 11, 2009
 */
public class CommandMessage extends Message {

    /** 消息长度 */
    protected int length;

    /** 消息ID */
    protected int id;

    /** 命令ID */
    protected int commandId;

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.Message#encode()
     */
    @Override
    public ByteBuffer encode() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.Message#decode(java.nio.ByteBuffer)
     */
    @Override
    public void decode(ByteBuffer message) {
        this.message = message;

        // TODO 解码
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
     * 获取命令ID
     * @return 命令ID
     */
    public int getCommandId() {
        return commandId;
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
