package org.wg.xcache.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.wg.xcache.Cache;
import org.wg.xcache.CacheStat;
import org.wg.xcache.util.XcacheConst;

/**
 * 记录缓存统计信息任务
 * @author enychen Jul 30, 2009
 */
public class LogCacheStatTask extends AbstractTask {

    /** 统计信息日志 */
    private static final Log logXcacheStat = LogFactory.getLog(XcacheConst.LOG_XCACHE_STAT);

    /** 缓存 */
    private Cache            cache;

    /**
     * 创建记录缓存统计信息任务
     * @param cache 缓存
     */
    public LogCacheStatTask(Cache cache) {
        this.cache = cache;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.task.AbstractTask#getName()
     */
    @Override
    public String getName() {
        return this.cache.getName() + "记录缓存统计信息任务";
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.task.AbstractTask#doRun()
     */
    @Override
    protected void doRun() {
        CacheStat cacheStat = this.cache.getCacheStat();

        if (logXcacheStat.isInfoEnabled()) {
            logXcacheStat.info(cacheStat);
        }
    }

}
