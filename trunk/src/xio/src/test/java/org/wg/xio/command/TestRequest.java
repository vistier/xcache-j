package org.wg.xio.command;

import java.nio.ByteBuffer;

/**
 * 测试请求
 * @author enychen Oct 19, 2009
 */
public class TestRequest extends CommandMessage {

    /** 测试字符 */
    private String test;

    /**
     * 创建测试请求
     */
    public TestRequest() {
    }

    /**
     * 创建测试请求
     * @param commandMessage 命令消息
     */
    public TestRequest(CommandMessage commandMessage) {
        this.copy(commandMessage);
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.command.CommandMessage#encode()
     */
    @Override
    public ByteBuffer encode() {
        byte[] testBytes = this.test.getBytes();
        this.length = HEADER_LENGTH + testBytes.length;

        ByteBuffer message = ByteBuffer.allocate(this.length);
        message.putInt(this.length);
        message.putShort(this.id);
        message.putShort(this.commandId);
        message.put(testBytes);
        message.flip();

        return message;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.command.CommandMessage#decode(java.nio.ByteBuffer)
     */
    @Override
    public void decode(ByteBuffer message) {
        byte[] testBytes = new byte[message.remaining()];
        message.get(testBytes);
        this.test = new String(testBytes);
    }

    /**
     * 获取测试字符
     * @return 测试字符
     */
    public String getTest() {
        return test;
    }

    /**
     * 设置测试字符
     * @param test 测试字符
     */
    public void setTest(String test) {
        this.test = test;
    }

}
