package org.wg.xcache;

import java.io.Serializable;

/**
 * 缓存元素
 * @author enychen Jul 19, 2009
 */
public class Element implements Serializable {

    /** serialVersionUID = 6918572989165480348L */
    private static final long serialVersionUID = 6918572989165480348L;

    /** 缓存键 */
    private Object            key;

    /** 缓存对象 */
    private Object            object;

    /** 创建时间，单位毫秒 */
    private long              createdTime;

    /** 最后访问时间，单位毫秒 */
    private long              lastAccessTime;

    /** 最后更新时间，单位毫秒 */
    private long              lastUpdateTime;

    /** 访问次数 */
    private long              accessTimes;

    /** 更新次数 */
    private long              updateTimes;

    /** 生存时间，单位毫秒 */
    private long              liveTime;

    /** 空闲时间，单位毫秒 */
    private long              idleTime;

    /**
     * 创建缓存元素
     * @param key 缓存键
     * @param object 缓存对象
     */
    public Element(Object key, Object object) {
        this(key, object, 0L, 0L);
    }

    /**
     * 创建缓存元素
     * @param key 缓存键
     * @param object 缓存对象
     * @param liveTime 生存时间，单位毫秒
     * @param idleTime 空闲时间，单位毫秒
     */
    public Element(Object key, Object object, long liveTime, long idleTime) {
        this.key = key;
        this.object = object;
        this.liveTime = liveTime;
        this.idleTime = idleTime;
        this.createdTime = System.currentTimeMillis();
    }

    /**
     * 是否过期
     * @return true：已经过期，false：没有过期
     */
    public boolean isExpire() {
        boolean result = false;
        long now = System.currentTimeMillis();

        // -----------------------------------
        // 使用生存时间和空闲时间判断是否过期
        // -----------------------------------

        if (this.liveTime > 0L) {
            // 距离创建时间超过生存时间
            if (now - this.createdTime > this.liveTime) {
                result = true;
            }
        }

        if (this.idleTime > 0L) {
            // 如果元素被访问过，用距离最后访问时间和空闲时间比较
            if (this.lastAccessTime > 0L) {
                // 距离最后访问时间超过空闲时间
                if (now - this.lastAccessTime > this.idleTime) {
                    result = true;
                }
            }
            // 如果元素没有被访问过，用距离创建时间和空闲时间比较
            else {
                // 距离创建时间超过空闲时间
                if (now - this.createdTime > this.idleTime) {
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * 刷新访问统计
     */
    public void refreshAccessStat() {
        this.lastAccessTime = System.currentTimeMillis();
        this.accessTimes++;
    }

    /**
     * 刷新更新统计
     */
    public void refreshUpdateStat() {
        this.lastUpdateTime = System.currentTimeMillis();
        this.updateTimes++;
    }

    /**
     * 获取缓存键
     * @return 缓存键
     */
    public Object getKey() {
        return key;
    }

    /**
     * 获取缓存对象
     * @return 缓存对象
     */
    public Object getObject() {
        return object;
    }

    /**
     * 设置缓存对象
     * @param object 缓存对象
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * 获取创建时间，单位毫秒
     * @return 创建时间，单位毫秒
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * 获取最后访问时间，单位毫秒
     * @return 最后访问时间，单位毫秒
     */
    public long getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * 获取最后更新时间，单位毫秒
     * @return 最后更新时间，单位毫秒
     */
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 获取访问次数
     * @return 访问次数
     */
    public long getAccessTimes() {
        return accessTimes;
    }

    /**
     * 获取更新次数
     * @return 更新次数
     */
    public long getUpdateTimes() {
        return updateTimes;
    }

    /**
     * 获取生存时间，单位毫秒
     * @return 生存时间，单位毫秒
     */
    public long getLiveTime() {
        return liveTime;
    }

    /**
     * 获取空闲时间，单位毫秒
     * @return 空闲时间，单位毫秒
     */
    public long getIdleTime() {
        return idleTime;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "key=" + this.key;
    }

}
