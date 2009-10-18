package org.wg.xcache.server.context;

/**
 * 请求头
 * @author enychen Sep 6, 2009
 */
public class RequestHeader {

    /** 消息长度 */
    protected int  length;

    /** 命令 */
    protected byte command;

    /**
     * 获取消息长度
     * @return 消息长度
     */
    public int getLength() {
        return length;
    }

    /**
     * 获取命令
     * @return 命令
     */
    public byte getCommand() {
        return command;
    }

    /**
     * 解码
     * @param bytes byte数组
     */
    public void decode(byte[] bytes) {

    }

}
