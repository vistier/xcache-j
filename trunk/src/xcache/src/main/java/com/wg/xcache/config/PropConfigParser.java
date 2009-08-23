package com.wg.xcache.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import com.wg.xcache.XcacheException;
import com.wg.xcache.util.XcacheConst;

/**
 * properties文件配置解析器
 * @author enychen Aug 9, 2009
 */
public class PropConfigParser implements ConfigParser {

    /** 缓存最大元素数量 */
    public static final String MAX_AMOUNT                     = "cacheConfig.{0}.maxAmount";

    /** 删除过期缓存元素延迟 */
    public static final String DELETE_EXPIRE_ELEMENT_DELAY    = "cacheConfig.{0}.deleteExpireElementDelay";

    /** 删除过期缓存元素间隔 */
    public static final String DELETE_EXPIRE_ELEMENT_INTERVAL = "cacheConfig.{0}.deleteExpireElementInterval";

    /** 记录缓存统计信息延迟 */
    public static final String LOG_CACHE_STAT_DELAY           = "cacheConfig.{0}.logCacheStatDelay";

    /** 记录缓存统计信息间隔 */
    public static final String LOG_CACHE_STAT_INTERVAL        = "cacheConfig.{0}.logCacheStatInterval";

    /** 存储媒介 */
    public static final String STORE_MEDIA                    = "cacheConfig.{0}.storeMedia";

    /** 退出策略类型 */
    public static final String OUT_POLICY_TYPE                = "cacheConfig.{0}.outPolicyType";

    /** 配置 */
    private Properties         properties                     = new Properties();

    /*
     * (non-Javadoc)
     * @see com.wg.xcache.config.ConfigParser#parse()
     */
    public Config parse() {
        Config config = new Config();
        InputStream inputStream = this.getClass()
                .getResourceAsStream(XcacheConst.XCACHE_PROPERTIES);

        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            throw new XcacheException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new XcacheException(e);
            }
        }

        config.setCacheConfigs(this.parseCacheConfigs());

        return config;
    }

    /**
     * 解析缓存配置
     * @return 缓存配置
     */
    private CacheConfig[] parseCacheConfigs() {
        String caches = this.properties.getProperty(XcacheConst.XCACHE_CACHES);
        String[] cacheNames = caches.split(",");
        CacheConfig[] cacheConfigs = new CacheConfig[cacheNames.length];

        for (int i = 0; i < cacheNames.length; i++) {
            cacheConfigs[i] = this.parseCacheConfig(cacheNames[i]);
        }

        return cacheConfigs;
    }

    /**
     * 解析缓存配置
     * @param name 缓存名
     * @return 缓存配置
     */
    private CacheConfig parseCacheConfig(String name) {
        CacheConfig cacheConfig = new CacheConfig();

        String maxAmount = properties.getProperty(MessageFormat.format(MAX_AMOUNT, name));
        String deleteExpireElementDelay = properties.getProperty(MessageFormat.format(
                DELETE_EXPIRE_ELEMENT_DELAY, name));
        String deleteExpireElementInterval = properties.getProperty(MessageFormat.format(
                DELETE_EXPIRE_ELEMENT_INTERVAL, name));
        String logCacheStatDelay = properties.getProperty(MessageFormat.format(
                LOG_CACHE_STAT_DELAY, name));
        String logCacheStatInterval = properties.getProperty(MessageFormat.format(
                LOG_CACHE_STAT_INTERVAL, name));
        String storeMedia = properties.getProperty(MessageFormat.format(STORE_MEDIA, name));
        String outPolicyType = properties.getProperty(MessageFormat.format(OUT_POLICY_TYPE, name));

        cacheConfig.setName(name);
        cacheConfig.setMaxAmount(maxAmount);
        cacheConfig.setDeleteExpireElementDelay(deleteExpireElementDelay);
        cacheConfig.setDeleteExpireElementInterval(deleteExpireElementInterval);
        cacheConfig.setLogCacheStatDelay(logCacheStatDelay);
        cacheConfig.setLogCacheStatInterval(logCacheStatInterval);
        cacheConfig.setStoreMedia(storeMedia);
        cacheConfig.setOutPolicyType(outPolicyType);

        return cacheConfig;
    }

}
