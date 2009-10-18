package org.wg.xcache;

/**
 * 缓存接口
 * @author enychen Jul 19, 2009
 */
public interface Cache {

    /**
     * 获取缓存名
     * @return 缓存名
     */
    String getName();

    /**
     * 把缓存元素放入缓存
     * @param element 缓存元素
     */
    void put(Element element);

    /**
     * 删除缓存中的缓存元素
     * @param key 缓存键
     */
    void delete(Object key);

    /**
     * 获取缓存中的缓存元素
     * @param key 缓存键
     * @return 缓存元素
     */
    Element get(Object key);

    /**
     * 清除缓存
     */
    void clear();

    /**
     * 获取缓存统计信息
     * @return 缓存统计信息
     */
    CacheStat getCacheStat();

    /**
     * 删除过期缓存元素
     * @return 删除数量
     */
    int deleteExpireElement();

}
