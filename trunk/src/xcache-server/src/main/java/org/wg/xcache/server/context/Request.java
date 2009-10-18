package org.wg.xcache.server.context;

/**
 * 请求
 * @author enychen Sep 6, 2009
 */
public abstract class Request {

    /** 请求头 */
    protected RequestHeader requestHeader;

    /**
     * 获取请求头
     * @return 请求头
     */
    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    /**
     * 设置请求头
     * @param requestHeader 请求头
     */
    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    /**
     * 解码
     * @param bytes byte数组
     */
    public abstract void decode(byte[] bytes);

}
