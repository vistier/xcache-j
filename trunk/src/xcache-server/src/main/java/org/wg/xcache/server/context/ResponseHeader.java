package com.wg.xcache.server.context;

/**
 * 响应头
 * @author enychen Sep 7, 2009
 */
public class ResponseHeader {

    /** 消息长度 */
    protected int length;

    /**
     * 设置消息长度
     * @param length 消息长度
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * 编码
     * @return byte数组
     */
    public byte[] encode() {
        return null;
    }

}
