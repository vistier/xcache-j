package org.wg.xcache;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.wg.xcache.config.CacheConfig;
import org.wg.xcache.config.ConfigFactory;
import org.wg.xcache.util.XcacheConst;

/**
 * Xcache缓存管理器
 * @author enychen Jul 30, 2009
 */
public class XcacheManager {

    /** log */
    private static final Log           log           = LogFactory.getLog(XcacheManager.class);

    /** Xcache缓存管理器 */
    private static final XcacheManager xcacheManager = new XcacheManager();

    /** N套缓存Map */
    private Map<String, CacheSuite>    cacheSuiteMap = new ConcurrentHashMap<String, CacheSuite>();

    /**
     * 创建Xcache缓存管理器
     */
    private XcacheManager() {
        try {
            this.init();
        } catch (Exception e) {
            log.error("Xcache初始化异常！", e);
        }
    }

    /**
     * 获取Xcache缓存管理器实例
     * @return Xcache缓存管理器实例
     */
    public static XcacheManager getInstance() {
        return xcacheManager;
    }

    /**
     * 获取缓存
     * @param name 缓存名
     * @return 缓存
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
     * <p>
     * 步骤：<br>
     * 1、获取缓存配置<br>
     * 2、创建缓存<Br>
     * 3、初始化删除过期缓存元素任务<br>
     * 4、初始化记录缓存统计信息任务
     */
    private void init() {
        if (log.isInfoEnabled()) {
            log.info(MessageFormat.format(XcacheConst.LOG_REGION, "Xcache正在初始化"));
        }

        CacheConfig[] cacheConfigs = ConfigFactory.getInstance().getConfig().getCacheConfigs();

        if (cacheConfigs != null) {
            for (CacheConfig cacheConfig : cacheConfigs) {
                CacheSuite cacheSuite = CacheFactory.getInstance().getCacheSuite(cacheConfig);
                this.cacheSuiteMap.put(cacheConfig.getName(), cacheSuite);

                if (log.isInfoEnabled()) {
                    log.info(cacheConfig.getName() + "初始化完成，" + cacheConfig);
                }
            }
        }

        if (log.isInfoEnabled()) {
            log.info(MessageFormat.format(XcacheConst.LOG_REGION, "Xcahce初始化完成"));
        }
    }

}
