package org.wg.xcache;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 缓存统计信息
 * @author enychen Jul 30, 2009
 */
public class CacheStat {

    /** 缓存名 */
    private String           name;

    /** 创建时间，单位毫秒 */
    private long             createdTime = System.currentTimeMillis();

    /** 缓存元素数量 */
    private int              amount;

    /** 最后放入缓存时间，单位毫秒 */
    private long             lastPutTime;

    /** 最后删除缓存时间，单位毫秒 */
    private long             lastDeleteTime;

    /** 最后获取缓存时间，单位毫秒 */
    private long             lastAccessTime;

    /** 最后清除缓存时间，单位毫秒 */
    private long             lastClearTime;

    /** 最后获取缓存统计信息时间，单位毫秒 */
    private long             lastGetCacheStatTime;

    /** 最后删除过期缓存元素时间，单位毫秒 */
    private long             lastDeleteExpireElementTime;

    /** 放入缓存次数 */
    private long             putTimes;

    /** 抛弃元素次数 */
    private long             abandonTimes;

    /** 缓存退出次数 */
    private long             outTimes;

    /** 插入缓存次数 */
    private long             insertTimes;

    /** 更新缓存次数 */
    private long             updateTimes;

    /** 删除缓存次数 */
    private long             deleteTimes;

    /** 获取缓存次数 */
    private long             accessTimes;

    /** 清除缓存次数 */
    private long             clearTimes;

    /** 获取缓存统计信息次数 */
    private long             getCacheStatTimes;

    /** 删除过期缓存元素次数 */
    private long             deleteExpireElementTimes;

    /** 时间格式，yyyy-MM-dd HH:mm:ss */
    private SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 创建缓存统计信息
     * @param name 缓存名
     */
    public CacheStat(String name) {
        this.name = name;
    }

    /**
     * 获取创建时间，单位毫秒
     * @return 创建时间，单位毫秒
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * 获取缓存元素数量
     * @return 缓存元素数量
     */
    public int getAmount() {
        return amount;
    }

    /**
     * 设置缓存元素数量
     * @param amount 缓存元素数量
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * 获取最后放入缓存时间，单位毫秒
     * @return 最后放入缓存时间，单位毫秒
     */
    public long getLastPutTime() {
        return lastPutTime;
    }

    /**
     * 设置最后放入缓存时间，单位毫秒
     * @param lastPutTime 最后放入缓存时间，单位毫秒
     */
    public void setLastPutTime(long lastPutTime) {
        this.lastPutTime = lastPutTime;
    }

    /**
     * 获取最后删除缓存时间，单位毫秒
     * @return 最后删除缓存时间，单位毫秒
     */
    public long getLastDeleteTime() {
        return lastDeleteTime;
    }

    /**
     * 设置最后删除缓存时间，单位毫秒
     * @param lastDeleteTime 最后删除缓存时间，单位毫秒
     */
    public void setLastDeleteTime(long lastDeleteTime) {
        this.lastDeleteTime = lastDeleteTime;
    }

    /**
     * 获取最后获取缓存时间，单位毫秒
     * @return 最后获取缓存时间，单位毫秒
     */
    public long getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * 设置最后获取缓存时间，单位毫秒
     * @param lastAccessTime 最后获取缓存时间，单位毫秒
     */
    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * 获取最后清除缓存时间，单位毫秒
     * @return 最后清除缓存时间，单位毫秒
     */
    public long getLastClearTime() {
        return lastClearTime;
    }

    /**
     * 设置最后清除缓存时间，单位毫秒
     * @param lastClearTime 最后清除缓存时间，单位毫秒
     */
    public void setLastClearTime(long lastClearTime) {
        this.lastClearTime = lastClearTime;
    }

    /**
     * 获取最后获取缓存统计信息时间，单位毫秒
     * @return 最后获取缓存统计信息时间，单位毫秒
     */
    public long getLastGetCacheStatTime() {
        return lastGetCacheStatTime;
    }

    /**
     * 设置最后获取缓存统计信息时间，单位毫秒
     * @param lastGetCacheStatTime 最后获取缓存统计信息时间，单位毫秒
     */
    public void setLastGetCacheStatTime(long lastGetCacheStatTime) {
        this.lastGetCacheStatTime = lastGetCacheStatTime;
    }

    /**
     * 获取最后删除过期缓存元素时间，单位毫秒
     * @return 最后删除过期缓存元素时间，单位毫秒
     */
    public long getLastDeleteExpireElementTime() {
        return lastDeleteExpireElementTime;
    }

    /**
     * 设置最后删除过期缓存元素时间，单位毫秒
     * @param lastDeleteExpireElementTime 最后删除过期缓存元素时间，单位毫秒
     */
    public void setLastDeleteExpireElementTime(long lastDeleteExpireElementTime) {
        this.lastDeleteExpireElementTime = lastDeleteExpireElementTime;
    }

    /**
     * 获取放入缓存次数
     * @return 放入缓存次数
     */
    public long getPutTimes() {
        return putTimes;
    }

    /**
     * 增加放入缓存次数
     */
    public void increasePutTimes() {
        this.putTimes++;
    }

    /**
     * 获取抛弃元素次数
     * @return 抛弃元素次数
     */
    public long getAbandonTimes() {
        return abandonTimes;
    }

    /**
     * 增加抛弃元素次数
     */
    public void increaseAbandonTimes() {
        this.abandonTimes++;
    }

    /**
     * 获取缓存退出次数
     * @return 缓存退出次数
     */
    public long getOutTimes() {
        return outTimes;
    }

    /**
     * 增加缓存退出次数
     */
    public void increaseOutTimes() {
        this.outTimes++;
    }

    /**
     * 获取插入缓存次数
     * @return 插入缓存次数
     */
    public long getInsertTimes() {
        return insertTimes;
    }

    /**
     * 增加插入缓存次数
     */
    public void increaseInsertTimes() {
        this.insertTimes++;
    }

    /**
     * 获取更新缓存次数
     * @return 更新缓存次数
     */
    public long getUpdateTimes() {
        return updateTimes;
    }

    /**
     * 增加更新缓存次数
     */
    public void increaseUpdateTimes() {
        this.updateTimes++;
    }

    /**
     * 获取删除缓存次数
     * @return 删除缓存次数
     */
    public long getDeleteTimes() {
        return deleteTimes;
    }

    /**
     * 增加删除缓存次数
     */
    public void increaseDeleteTimes() {
        this.deleteTimes++;
    }

    /**
     * 获取获取缓存次数
     * @return 获取缓存次数
     */
    public long getAccessTimes() {
        return accessTimes;
    }

    /**
     * 增加获取缓存次数
     */
    public void increaseAccessTimes() {
        this.accessTimes++;
    }

    /**
     * 获取清除缓存次数
     * @return 清除缓存次数
     */
    public long getClearTimes() {
        return clearTimes;
    }

    /**
     * 增加清除缓存次数
     */
    public void increaseClearTimes() {
        this.clearTimes++;
    }

    /**
     * 获取 获取缓存统计信息次数
     * @return 获取缓存统计信息次数
     */
    public long getGetCacheStatTimes() {
        return getCacheStatTimes;
    }

    /**
     * 增加获取缓存统计信息次数
     */
    public void increaseGetCacheStatTimes() {
        this.getCacheStatTimes++;
    }

    /**
     * 获取删除过期缓存元素次数
     * @return 删除过期缓存元素次数
     */
    public long getDeleteExpireElementTimes() {
        return deleteExpireElementTimes;
    }

    /**
     * 增加删除过期缓存元素次数
     */
    public void increaseDeleteExpireElementTimes() {
        this.deleteExpireElementTimes++;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder stat = new StringBuilder();
        stat.append(this.name).append("缓存统计信息: ");
        stat.append("createdTime=").append(this.ConvertTimeToString(this.createdTime)).append(", ");
        stat.append("amount=").append(this.amount).append(", ");
        stat.append("lastPutTime=").append(this.ConvertTimeToString(this.lastPutTime)).append(", ");
        stat.append("lastDeleteTime=").append(this.ConvertTimeToString(this.lastDeleteTime))
                .append(", ");
        stat.append("lastAccessTime=").append(this.ConvertTimeToString(this.lastAccessTime))
                .append(", ");
        stat.append("lastClearTime=").append(this.ConvertTimeToString(this.lastClearTime)).append(
                ", ");
        stat.append("lastGetCacheStatTime=").append(
                this.ConvertTimeToString(this.lastGetCacheStatTime)).append(", ");
        stat.append("lastDeleteExpireElementTime=").append(
                this.ConvertTimeToString(this.lastDeleteExpireElementTime)).append(", ");
        stat.append("putTimes=").append(this.putTimes).append(", ");
        stat.append("abandonTimes=").append(this.abandonTimes).append(", ");
        stat.append("outTimes=").append(this.outTimes).append(", ");
        stat.append("insertTimes=").append(this.insertTimes).append(", ");
        stat.append("updateTimes=").append(this.updateTimes).append(", ");
        stat.append("deleteTimes=").append(this.deleteTimes).append(", ");
        stat.append("accessTimes=").append(this.accessTimes).append(", ");
        stat.append("clearTimes=").append(this.clearTimes).append(", ");
        stat.append("getCacheStatTimes=").append(this.getCacheStatTimes).append(", ");
        stat.append("deleteExpireElementTimes=").append(this.deleteExpireElementTimes).append(".");

        return stat.toString();
    }

    /**
     * 将时间转换成字符串
     * @param time 时间
     * @return 时间字符串
     */
    private String ConvertTimeToString(long time) {
        String result;

        if (time > 0L) {
            result = this.dateFormat.format(new Date(time));
        } else {
            result = "null";
        }

        return result;
    }

}
