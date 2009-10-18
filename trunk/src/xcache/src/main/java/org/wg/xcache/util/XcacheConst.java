package org.wg.xcache.util;

/**
 * 常量类
 * @author enychen Jul 31, 2009
 */
public class XcacheConst {

    /** MAP初始容量，10000 */
    public static final int    MAP_INIT_CAPACITY     = 10000;

    /** MAP加载因子，0.75 */
    public static final float  MAP_LOAD_FACTOR       = 0.75f;

    /** MAP并发级别，100 */
    public static final int    MAP_CONCURRENCY_LEVEL = 100;

    /** 缓存配置文件 */
    public static final String XCACHE_PROPERTIES     = "/xcache.properties";

    /** 缓存 */
    public static final String XCACHE_CACHES         = "xcache.caches";

    /** 日志区域 */
    public static final String LOG_REGION            = "===================={0}====================";

    /** 统计信息日志 */
    public static final String LOG_XCACHE_STAT       = "XCACHE-STAT";

}
