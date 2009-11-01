package org.wg.xio.command;

import java.nio.ByteBuffer;

import org.wg.xio.ex.command.CommandResponse;
import org.wg.xio.util.XioConst;

/**
 * 测试响应
 * @author enychen Nov 1, 2009
 */
public class TestResponse extends CommandResponse {

    /** 测试字符 */
    private String test;

    /**
     * 创建测试响应
     */
    public TestResponse() {
    }
    
    /**
     * 测试响应
     * @param commandResponse 命令响应
     */
    public TestResponse(CommandResponse commandResponse) {
        this.copy(commandResponse);
    }
    
    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandResponse#encode()
     */
    @Override
    public ByteBuffer encode() {
        byte[] testBytes = this.test.getBytes();
        this.length = XioConst.COMMAND_RESPONSE_HEADER_LENGTH + testBytes.length;
        
        ByteBuffer message = ByteBuffer.allocate(this.length);
        message.putInt(this.length);
        message.putInt(this.id);
        message.put(testBytes);
        message.flip();

        return message;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandResponse#decode(java.nio.ByteBuffer)
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
