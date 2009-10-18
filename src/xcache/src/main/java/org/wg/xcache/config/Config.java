package org.wg.xcache.config;

/**
 * 配置
 * @author enychen Aug 9, 2009
 */
public class Config {

    /** 缓存配置 */
    private CacheConfig[] cacheConfigs = new CacheConfig[0];

    /**
     * 获取缓存配置
     * @return 缓存配置
     */
    public CacheConfig[] getCacheConfigs() {
        return cacheConfigs;
    }

    /**
     * 设置缓存配置
     * @param cacheConfigs 缓存配置
     */
    public void setCacheConfigs(CacheConfig[] cacheConfigs) {
        this.cacheConfigs = cacheConfigs;
    }

}
