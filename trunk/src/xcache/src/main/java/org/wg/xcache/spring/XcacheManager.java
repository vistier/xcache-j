package org.wg.xcache.spring;

import org.wg.xcache.Cache;

/**
 * Xcache缓存管理器
 * @author enychen Nov 3, 2009
 */
public interface XcacheManager {

    /**
     * 获取缓存
     * @param name 缓存名
     * @return 缓存
     */
    Cache getCache(String name);

}