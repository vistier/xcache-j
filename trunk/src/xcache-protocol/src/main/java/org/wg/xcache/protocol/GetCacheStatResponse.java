package org.wg.xcache.protocol;

import java.io.Serializable;

import org.wg.xcache.CacheStat;

/**
 * 获取缓存统计信息响应
 * @author enychen Nov 12, 2009
 */
public class GetCacheStatResponse implements Serializable {

    /** serialVersionUID = 607099269572345461L */
    private static final long serialVersionUID = 607099269572345461L;

    /** 缓存统计信息 */
    protected CacheStat       cacheStat;

    /**
     * 获取缓存统计信息
     * @return 缓存统计信息
     */
    public CacheStat getCacheStat() {
        return cacheStat;
    }

    /**
     * 设置缓存统计信息
     * @param cacheStat 缓存统计信息
     */
    public void setCacheStat(CacheStat cacheStat) {
        this.cacheStat = cacheStat;
    }

}
