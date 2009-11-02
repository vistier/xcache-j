package org.wg.xio.command;

import java.nio.ByteBuffer;

import org.wg.xio.ex.command.CommandRequest;
import org.wg.xio.util.XioConst;

/**
 * 测试请求
 * @author enychen Oct 19, 2009
 */
public class TestRequest extends CommandRequest {

    /** 测试字符 */
    private String test;

    /**
     * 创建测试请求
     */
    public TestRequest() {
    }

    /**
     * 创建测试请求
     * @param commandRequest 命令请求
     */
    public TestRequest(CommandRequest commandRequest) {
        this.copy(commandRequest);
        this.decode(commandRequest.getMessage());
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.command.CommandRequest#encode()
     */
    @Override
    public ByteBuffer encode() {
        byte[] testBytes = this.test.getBytes();
        this.length = XioConst.COMMAND_REQUEST_HEADER_LENGTH + testBytes.length;

        ByteBuffer message = ByteBuffer.allocate(this.length);
        message.putInt(this.length);
        message.putInt(this.id);
        message.putInt(this.commandId);
        message.put(testBytes);
        message.flip();

        return message;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.command.CommandRequest#decode(java.nio.ByteBuffer)
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
