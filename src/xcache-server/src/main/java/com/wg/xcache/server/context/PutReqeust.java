package com.wg.xcache.server.context;

/**
 * 放入缓存请求
 * @author enychen Sep 6, 2009
 */
public class PutReqeust extends Request {

    /** 缓存名 */
    protected String cacheName;

    /** 缓存键 */
    protected String key;

    /** 缓存对象 */
    protected Object object;

    /** 生存时间，单位毫秒 */
    protected int    liveTime;

    /** 空闲时间，单位毫秒 */
    protected int    idleTime;

    /*
     * (non-Javadoc)
     * @see com.wg.xcache.server.context.Request#decode(byte[])
     */
    @Override
    public void decode(byte[] bytes) {
        // TODO Auto-generated method stub

    }

}
