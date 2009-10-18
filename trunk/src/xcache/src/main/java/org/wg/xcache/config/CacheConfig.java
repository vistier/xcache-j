package com.wg.xcache.config;

import com.wg.xcache.out.OutPolicyType;
import com.wg.xcache.store.StoreMedia;

/**
 * 缓存配置
 * @author enychen Jul 30, 2009
 */
public class CacheConfig {

    /** 缓存名，默认default */
    private String        name       = "default";

    /** 缓存最大元素数量，默认10000 */
    private int           maxAmount  = 10000;

    /** 删除过期缓存元素延迟，单位毫秒 */
    private long          deleteExpireElementDelay;

    /** 删除过期缓存元素间隔，单位毫秒 */
    private long          deleteExpireElementInterval;

    /** 记录缓存统计信息延迟，单位毫秒 */
    private long          logCacheStatDelay;

    /** 记录缓存统计信息间隔，单位毫秒 */
    private long          logCacheStatInterval;

    /** 存储媒介，默认内存 */
    private StoreMedia    storeMedia = StoreMedia.MEMORY;

    /** 退出策略类型 */
    private OutPolicyType outPolicyType;

    /**
     * 获取缓存名
     * @return 缓存名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置缓存名
     * @param name 缓存名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取缓存最大元素数量
     * @return 缓存最大元素数量
     */
    public int getMaxAmount() {
        return maxAmount;
    }

    /**
     * 设置缓存最大元素数量
     * @param maxAmount 缓存最大元素数量
     */
    public void setMaxAmount(String maxAmount) {
        if (maxAmount != null) {
            this.maxAmount = Integer.parseInt(maxAmount);
        }
    }

    /**
     * 获取删除过期缓存元素延迟，单位毫秒
     * @return 删除过期缓存元素延迟，单位毫秒
     */
    public long getDeleteExpireElementDelay() {
        return deleteExpireElementDelay;
    }

    /**
     * 设置删除过期缓存元素延迟，单位毫秒
     * @param deleteExpireElementDelay 删除过期缓存元素延迟，单位毫秒
     */
    public void setDeleteExpireElementDelay(String deleteExpireElementDelay) {
        if (deleteExpireElementDelay != null) {
            this.deleteExpireElementDelay = Long.parseLong(deleteExpireElementDelay);
        }
    }

    /**
     * 获取删除过期缓存元素间隔，单位毫秒
     * @return 删除过期缓存元素间隔，单位毫秒
     */
    public long getDeleteExpireElementInterval() {
        return deleteExpireElementInterval;
    }

    /**
     * 设置删除过期缓存元素间隔，单位毫秒
     * @param deleteExpireElementInterval 删除过期缓存元素间隔，单位毫秒
     */
    public void setDeleteExpireElementInterval(String deleteExpireElementInterval) {
        if (deleteExpireElementInterval != null) {
            this.deleteExpireElementInterval = Long.parseLong(deleteExpireElementInterval);
        }
    }

    /**
     * 获取记录缓存统计信息延迟，单位毫秒
     * @return 记录缓存统计信息延迟，单位毫秒
     */
    public long getLogCacheStatDelay() {
        return logCacheStatDelay;
    }

    /**
     * 设置记录缓存统计信息延迟，单位毫秒
     * @param logCacheStatDelay 记录缓存统计信息延迟，单位毫秒
     */
    public void setLogCacheStatDelay(String logCacheStatDelay) {
        if (logCacheStatDelay != null) {
            this.logCacheStatDelay = Long.parseLong(logCacheStatDelay);
        }
    }

    /**
     * 获取记录缓存统计信息间隔，单位毫秒
     * @return 记录缓存统计信息间隔，单位毫秒
     */
    public long getLogCacheStatInterval() {
        return logCacheStatInterval;
    }

    /**
     * 设置记录缓存统计信息间隔，单位毫秒
     * @param logCacheStatInterval 记录缓存统计信息间隔，单位毫秒
     */
    public void setLogCacheStatInterval(String logCacheStatInterval) {
        if (logCacheStatInterval != null) {
            this.logCacheStatInterval = Long.parseLong(logCacheStatInterval);
        }
    }

    /**
     * 获取存储媒介
     * @return 存储媒介
     */
    public StoreMedia getStoreMedia() {
        return storeMedia;
    }

    /**
     * 设置存储媒介
     * @param storeMedia 存储媒介
     */
    public void setStoreMedia(String storeMedia) {
        if (storeMedia != null) {
            this.storeMedia = StoreMedia.valueOf(storeMedia);
        }
    }

    /**
     * 获取退出策略类型
     * @return 退出策略类型
     */
    public OutPolicyType getOutpolicyType() {
        return outPolicyType;
    }

    /**
     * 设置退出策略类型
     * @param outPolicyType 退出策略类型
     */
    public void setOutPolicyType(String outPolicyType) {
        if (outPolicyType != null) {
            this.outPolicyType = OutPolicyType.valueOf(outPolicyType);
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder config = new StringBuilder();
        config.append("缓存配置: ");
        config.append("name=").append(this.name).append(", ");
        config.append("maxAmount=").append(this.maxAmount).append(", ");
        config.append("deleteExpireElementDelay=").append(this.deleteExpireElementDelay).append(
                ", ");
        config.append("deleteExpireElementInterval=").append(this.deleteExpireElementInterval)
                .append(", ");
        config.append("logCacheStatDelay=").append(this.logCacheStatDelay).append(", ");
        config.append("logCacheStatInterval=").append(this.logCacheStatInterval).append(", ");
        config.append("storeMedia=").append(this.storeMedia).append(", ");
        config.append("outPolicyType=").append(this.outPolicyType).append(".");

        return config.toString();
    }

}
