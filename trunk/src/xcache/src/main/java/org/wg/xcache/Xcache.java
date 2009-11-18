package org.wg.xcache;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.wg.xcache.config.CacheConfig;
import org.wg.xcache.out.OutPolicy;
import org.wg.xcache.out.OutPolicyFactory;
import org.wg.xcache.store.Store;
import org.wg.xcache.store.StoreFactory;

/**
 * Xcache缓存实现
 * @author enychen Jul 19, 2009
 */
public final class Xcache implements Cache {

    /** log */
    private static final Log log = LogFactory.getLog(Xcache.class);

    /** 存储器 */
    private Store            store;

    /** 退出策略 */
    private OutPolicy        outPolicy;

    /** 缓存配置 */
    private CacheConfig      cacheConfig;

    /** 缓存统计信息 */
    private CacheStat        cacheStat;

    /**
     * 创建Xcache缓存实现
     * @param cacheConfig 缓存配置
     */
    public Xcache(CacheConfig cacheConfig) {
        this.store = StoreFactory.getInstance().getStore(cacheConfig.getStoreMedia());
        this.outPolicy = OutPolicyFactory.getInstance().getOutPolicy(cacheConfig.getOutPolicyType());
        this.cacheConfig = cacheConfig;
        this.cacheStat = new CacheStat(cacheConfig.getName());
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.Cache#getName()
     */
    public String getName() {
        return this.cacheConfig.getName();
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.Cache#put(org.wg.xcache.Element)
     */
    public void put(Element element) {
        this.cacheStat.setLastPutTime(System.currentTimeMillis());
        this.cacheStat.increasePutTimes();

        Element existElement = this.store.get(element.getKey());

        if (existElement == null) {
            if (this.isFull()) {
                if (this.outPolicy == null) {
                    this.cacheStat.increaseAbandonTimes();

                    if (log.isInfoEnabled()) {
                        log.info(this.getName() + "缓存抛弃了元素" + element.getKey());
                    }
                } else {
                    // ------------------------
                    // 有退出策略：
                    // 1、选出一个元素退出缓存；
                    // 2、将新元素放入缓存。
                    // ------------------------
                    Element outElement = this.outPolicy.vote(store);

                    if (outElement != null) {
                        this.cacheStat.increaseOutTimes();

                        this.store.delete(outElement.getKey());
                        
                        if (log.isInfoEnabled()) {
                            log.info(this.getName() + "缓存已满，删除元素" + outElement.getKey());
                        }
                    }

                    // TODO outElement为null时，是否要考虑重试？

                    this.cacheStat.increaseInsertTimes();

                    this.store.put(element);
                }
            } else {
                this.cacheStat.increaseInsertTimes();

                this.store.put(element);
            }
        } else {
            this.cacheStat.increaseUpdateTimes();

            existElement.setObject(element.getObject());
            existElement.refreshUpdateStat();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.Cache#delete(java.lang.Object)
     */
    public void delete(Object key) {
        this.cacheStat.setLastDeleteTime(System.currentTimeMillis());
        this.cacheStat.increaseDeleteTimes();

        this.store.delete(key);
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.Cache#get(java.lang.Object)
     */
    public Element get(Object key) {
        this.cacheStat.setLastAccessTime(System.currentTimeMillis());
        this.cacheStat.increaseAccessTimes();

        Element element = this.store.get(key);

        if (element != null) {
            if (element.isExpire()) {
                this.store.delete(key);
                element = null;
            } else {
                element.refreshAccessStat();
            }
        }

        return element;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.Cache#clear()
     */
    public void clear() {
        this.cacheStat.setLastClearTime(System.currentTimeMillis());
        this.cacheStat.increaseClearTimes();

        this.store.clear();
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.Cache#getCacheStat()
     */
    public CacheStat getCacheStat() {
        this.cacheStat.setLastGetCacheStatTime(System.currentTimeMillis());
        this.cacheStat.increaseGetCacheStatTimes();

        this.cacheStat.setAmount(this.store.getAmount());

        return this.cacheStat;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.Cache#deleteExpireElement()
     */
    public int deleteExpireElement() {
        this.cacheStat.setLastDeleteExpireElementTime(System.currentTimeMillis());
        this.cacheStat.increaseDeleteExpireElementTimes();

        Collection<Element> elements = this.store.getAll();
        int count = 0;

        // TODO 高并发的情况下，迭代器是否会出现异常，有待验证。

        for (Element element : elements) {
            if (element.isExpire()) {
                this.store.delete(element.getKey());

                count++;
            }
        }

        return count;
    }

    /**
     * 缓存是否满了
     * @return true：满了，false：未满
     */
    private boolean isFull() {
        return (this.cacheConfig.getMaxAmount() > 0)
                && (this.store.getAmount() >= this.cacheConfig.getMaxAmount());
    }

}
