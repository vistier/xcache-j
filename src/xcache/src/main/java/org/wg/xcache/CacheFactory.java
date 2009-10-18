package org.wg.xcache;

import java.util.Timer;

import org.wg.xcache.config.CacheConfig;
import org.wg.xcache.task.AbstractTask;
import org.wg.xcache.task.DeleteExpireElementTask;
import org.wg.xcache.task.LogCacheStatTask;

/**
 * 缓存工厂
 * @author enychen Aug 9, 2009
 */
public class CacheFactory {

    /** 缓存工厂 */
    private static final CacheFactory cacheFactory = new CacheFactory();

    /**
     * 创建缓存工厂
     */
    private CacheFactory() {
    }

    /**
     * 获取缓存工厂实例
     * @return 缓存工厂实例
     */
    public static CacheFactory getInstance() {
        return cacheFactory;
    }

    /**
     * 获取一套缓存
     * @param cacheConfig 缓存配置
     * @return 一套缓存
     */
    public CacheSuite getCacheSuite(CacheConfig cacheConfig) {
        CacheSuite cacheSuite = new CacheSuite();

        Cache cache = this.createCache(cacheConfig);
        AbstractTask deleteExpireElementTask = this.initDeleteExpireElementTask(cacheConfig, cache);
        AbstractTask logCacheStatTask = this.initLogCacheStatTask(cacheConfig, cache);

        cacheSuite.setCache(cache);
        cacheSuite.setDeleteExpireElementTask(deleteExpireElementTask);
        cacheSuite.setLogCacheStatTask(logCacheStatTask);

        return cacheSuite;
    }

    /**
     * 创建缓存
     * @param cacheConfig 缓存配置
     * @return 缓存
     */
    private Cache createCache(CacheConfig cacheConfig) {
        Cache cache = new Xcache(cacheConfig);

        return cache;
    }

    /**
     * 初始化删除过期缓存元素任务
     * @param cacheConfig 缓存配置
     * @param cache 缓存
     * @return 删除过期缓存元素任务
     */
    private AbstractTask initDeleteExpireElementTask(CacheConfig cacheConfig, Cache cache) {
        AbstractTask task = null;

        if (cacheConfig.getDeleteExpireElementInterval() > 0L) {
            task = new DeleteExpireElementTask(cache);
            Timer timer = new Timer(cacheConfig.getName() + "-DeleteExpireElementTimer");

            timer.schedule(task, cacheConfig.getDeleteExpireElementDelay(), cacheConfig
                    .getDeleteExpireElementInterval());
        }

        return task;
    }

    /**
     * 初始化记录缓存统计信息任务
     * @param cacheConfig 缓存配置
     * @param cache 缓存
     * @return 记录缓存统计信息任务
     */
    private AbstractTask initLogCacheStatTask(CacheConfig cacheConfig, Cache cache) {
        AbstractTask task = null;

        if (cacheConfig.getLogCacheStatInterval() > 0L) {
            task = new LogCacheStatTask(cache);
            Timer timer = new Timer(cacheConfig.getName() + "-LogCacheStatTimer");

            timer.schedule(task, cacheConfig.getLogCacheStatDelay(), cacheConfig
                    .getLogCacheStatInterval());
        }

        return task;
    }

}
