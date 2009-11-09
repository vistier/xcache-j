package org.wg.xcache.protocol;

import java.io.Serializable;

/**
 * 把对象放入缓存请求
 * @author enychen Nov 2, 2009
 */
public class PutObjectRequest implements Serializable {

    /** serialVersionUID = 568816122135566167L */
    private static final long serialVersionUID = 568816122135566167L;

    /** 缓存名 */
    protected String          cacheName;

    /** 缓存键 */
    protected String          key;

    /** 缓存对象 */
    protected byte[]          object;

    /** 生存时间，单位毫秒 */
    protected int             liveTime;

    /** 空闲时间，单位毫秒 */
    protected int             idleTime;

    /**
     * 获取缓存名
     * @return 缓存名
     */
    public String getCacheName() {
        return cacheName;
    }

    /**
     * 设置缓存名
     * @param cacheName 缓存名
     */
    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * 获取缓存键
     * @return 缓存键
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置缓存键
     * @param key 缓存键
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取缓存对象
     * @return 缓存对象
     */
    public byte[] getObject() {
        return object;
    }

    /**
     * 设置缓存对象
     * @param object 缓存对象
     */
    public void setObject(byte[] object) {
        this.object = object;
    }

    /**
     * 获取生存时间，单位毫秒
     * @return 生存时间，单位毫秒
     */
    public int getLiveTime() {
        return liveTime;
    }

    /**
     * 设置生存时间，单位毫秒
     * @param liveTime 生存时间，单位毫秒
     */
    public void setLiveTime(int liveTime) {
        this.liveTime = liveTime;
    }

    /**
     * 空闲时间，单位毫秒
     * @return 空闲时间，单位毫秒
     */
    public int getIdleTime() {
        return idleTime;
    }

    /**
     * 设置空闲时间，单位毫秒
     * @param idleTime 空闲时间，单位毫秒
     */
    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

}
