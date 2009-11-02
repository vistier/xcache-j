package org.wg.xcache.spring;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xcache.Cache;
import org.wg.xcache.CacheFactory;
import org.wg.xcache.CacheSuite;
import org.wg.xcache.config.CacheConfig;
import org.wg.xcache.util.XcacheConst;

/**
 * Xcache缓存管理器实现
 * @author enychen Nov 1, 2009
 */
public class XcacheManagerImpl implements XcacheManager {

    /** log */
    private static final Log          log           = LogFactory.getLog(XcacheManagerImpl.class);

    /** 缓存配置 */
    protected CacheConfig[]           cacheConfigs;

    /** N套缓存Map */
    protected Map<String, CacheSuite> cacheSuiteMap = new ConcurrentHashMap<String, CacheSuite>();

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.spring.XcacheManager#getCache(java.lang.String)
     */
    public Cache getCache(String name) {
        Cache cache = null;
        CacheSuite cacheSuite = this.cacheSuiteMap.get(name);

        if (cacheSuite != null) {
            cache = cacheSuite.getCache();
        }

        return cache;
    }

    /**
     * 初始化
     */
    public void init() {
        if (log.isInfoEnabled()) {
            log.info(MessageFormat.format(XcacheConst.LOG_REGION, "Xcache正在初始化"));
        }

        for (CacheConfig cacheConfig : this.cacheConfigs) {
            CacheSuite cacheSuite = CacheFactory.getInstance().getCacheSuite(cacheConfig);
            this.cacheSuiteMap.put(cacheConfig.getName(), cacheSuite);

            if (log.isInfoEnabled()) {
                log.info(cacheConfig.getName() + "初始化完成，" + cacheConfig);
            }
        }

        if (log.isInfoEnabled()) {
            log.info(MessageFormat.format(XcacheConst.LOG_REGION, "Xcahce初始化完成"));
        }
    }

    // --Bean注入

    /**
     * 设置缓存配置
     * @param cacheConfigs 缓存配置
     */
    public void setCacheConfigs(CacheConfig[] cacheConfigs) {
        this.cacheConfigs = cacheConfigs;
    }

}
