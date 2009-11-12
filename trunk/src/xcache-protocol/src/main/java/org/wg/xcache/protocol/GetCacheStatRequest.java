package org.wg.xcache.protocol;

import java.io.Serializable;

/**
 * 获取缓存统计信息请求
 * @author enychen Nov 12, 2009
 */
public class GetCacheStatRequest implements Serializable {

    /** serialVersionUID = -5954116180800036630L */
    private static final long serialVersionUID = -5954116180800036630L;

    /** 缓存名 */
    protected String          cacheName;

    /**
     * 获取缓存名
     * @return 缓存名
     */
    public String getCacheName() {
        return cacheName;
    }

    /**
     * 设置缓存名
     * @param cacheName 缓存名
     */
    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

}
