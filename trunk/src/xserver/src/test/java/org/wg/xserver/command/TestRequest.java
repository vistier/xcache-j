package org.wg.xserver.command;

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
     * @param commandMessage 命令消息
     */
    public TestRequest(CommandMessage commandMessage) {
        this.copy(commandMessage);
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.command.CommandMessage#encode()
     */
    @Override
    public ByteBuffer encode() {
        // TODO Auto-generated method stub
        return super.encode();
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xserver.command.CommandMessage#decode(java.nio.ByteBuffer)
     */
    @Override
    public void decode(ByteBuffer message) {
        // TODO Auto-generated method stub
        super.decode(message);
    }

    /**
     * 获取测试字符
     * @return 测试字符
     */
    public String getTest() {
        return test;
    }

}
