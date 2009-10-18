package com.wg.xcache.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wg.xcache.Cache;

/**
 * 删除过期缓存元素任务
 * @author enychen Jul 30, 2009
 */
public class DeleteExpireElementTask extends AbstractTask {

    /** log */
    private static final Log log = LogFactory.getLog(DeleteExpireElementTask.class);

    /** 缓存 */
    private Cache            cache;

    /**
     * 创建删除过期缓存元素任务
     * @param cache 缓存
     */
    public DeleteExpireElementTask(Cache cache) {
        this.cache = cache;
    }

    /*
     * (non-Javadoc)
     * @see com.wg.xcache.task.AbstractTask#getName()
     */
    @Override
    public String getName() {
        return this.cache.getName() + "删除过期缓存元素任务";
    }

    /*
     * (non-Javadoc)
     * @see com.wg.xcache.task.AbstractTask#doRun()
     */
    @Override
    protected void doRun() {
        int count = this.cache.deleteExpireElement();

        if (log.isInfoEnabled()) {
            log.info(this.cache.getName() + "删除过期缓存元素" + count + "个。");
        }
    }

}
