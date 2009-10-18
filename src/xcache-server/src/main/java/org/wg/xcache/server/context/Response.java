package org.wg.xcache.server.context;

/**
 * 响应
 * @author enychen Sep 6, 2009
 */
public abstract class Response {

    /** 响应头 */
    protected ResponseHeader responseHeader;

    /**
     * 获取响应头
     * @return 响应头
     */
    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    /**
     * 设置响应头
     * @param responseHeader 响应头
     */
    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    /**
     * 编码
     * @return byte数组
     */
    public abstract byte[] encode();

}
