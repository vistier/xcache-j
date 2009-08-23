package com.wg.xcache;

import com.wg.xcache.task.AbstractTask;

/**
 * 一套缓存
 * @author enychen Aug 9, 2009
 */
public class CacheSuite {

    /** 缓存 */
    private Cache        cache;

    /** 删除过期缓存元素任务 */
    private AbstractTask deleteExpireElementTask;

    /** 记录缓存统计信息任务 */
    private AbstractTask logCacheStatTask;

    /**
     * 获取缓存
     * @return 缓存
     */
    public Cache getCache() {
        return cache;
    }

    /**
     * 设置缓存
     * @param cache 缓存
     */
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /**
     * 获取删除过期缓存元素任务
     * @return 删除过期缓存元素任务
     */
    public AbstractTask getDeleteExpireElementTask() {
        return deleteExpireElementTask;
    }

    /**
     * 设置删除过期缓存元素任务
     * @param deleteExpireElementTask 删除过期缓存元素任务
     */
    public void setDeleteExpireElementTask(AbstractTask deleteExpireElementTask) {
        this.deleteExpireElementTask = deleteExpireElementTask;
    }

    /**
     * 获取记录缓存统计信息任务
     * @return 记录缓存统计信息任务
     */
    public AbstractTask getLogCacheStatTask() {
        return logCacheStatTask;
    }

    /**
     * 设置记录缓存统计信息任务
     * @param logCacheStatTask 记录缓存统计信息任务
     */
    public void setLogCacheStatTask(AbstractTask logCacheStatTask) {
        this.logCacheStatTask = logCacheStatTask;
    }

}
