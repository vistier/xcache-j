package org.wg.xio.ex.command;

import java.nio.ByteBuffer;

import org.wg.xio.Message;
import org.wg.xio.util.XioConst;

/**
 * 命令请求
 * @author enychen Oct 11, 2009
 */
public class CommandRequest extends Message {

    /** 消息长度 */
    protected int length;

    /** 消息ID */
    protected int id;

    /** 命令ID */
    protected int commandId;

    /*
     * (non-Javadoc)
     * @see org.wg.xio.Message#encode()
     */
    @Override
    public ByteBuffer encode() {
        ByteBuffer message = ByteBuffer.allocate(XioConst.COMMAND_REQUEST_HEADER_LENGTH);
        message.putInt(XioConst.COMMAND_REQUEST_HEADER_LENGTH);
        message.putInt(this.id);
        message.putInt(this.commandId);
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
        this.commandId = message.getInt();
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
    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    /**
     * 拷贝命令请求
     * @param commandRequest 命令请求
     */
    public void copy(CommandRequest commandRequest) {
        this.length = commandRequest.length;
        this.id = commandRequest.id;
        this.commandId = commandRequest.commandId;
        this.message = commandRequest.message;
    }

}
