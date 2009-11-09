package org.wg.xio.ex.command;

import java.nio.ByteBuffer;

import org.wg.xio.util.XioConst;

/**
 * 可序列化对象请求
 * @author enychen Nov 3, 2009
 */
public class SerialObjectRequest extends CommandRequest {

    /** 序列化对象 */
    protected byte[] seObject;

    /**
     * 创建可序列化对象请求
     */
    public SerialObjectRequest() {
    }

    /**
     * 创建可序列化对象请求
     * @param commandRequest 命令请求
     */
    public SerialObjectRequest(CommandRequest commandRequest) {
        this.copy(commandRequest);
        this.decode(commandRequest.getMessage());
    }

    /**
     * 获取序列化对象
     * @return 序列化对象
     */
    public byte[] getSeObject() {
        return seObject;
    }

    /**
     * 设置序列化对象
     * @param seObject 序列化对象
     */
    public void setSeObject(byte[] seObject) {
        this.seObject = seObject;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandRequest#encode()
     */
    @Override
    public ByteBuffer encode() {
        this.length = XioConst.COMMAND_REQUEST_HEADER_LENGTH + this.seObject.length;

        ByteBuffer message = ByteBuffer.allocate(this.length);
        message.putInt(this.length);
        message.putInt(this.id);
        message.putInt(this.commandId);
        message.put(this.seObject);
        message.flip();

        return message;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandRequest#decode(java.nio.ByteBuffer)
     */
    @Override
    public void decode(ByteBuffer message) {
        this.seObject = new byte[message.remaining()];
        message.get(this.seObject);
    }

}
