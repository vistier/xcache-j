package org.wg.xcache.protocol;

import java.io.Serializable;

/**
 * 清除缓存请求
 * @author enychen Nov 12, 2009
 */
public class ClearCacheRequest implements Serializable {

    /** serialVersionUID = -7187674350439407429L */
    private static final long serialVersionUID = -7187674350439407429L;

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
