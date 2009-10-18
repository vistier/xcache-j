package org.wg.xcache.store;

import java.util.Collection;

import org.wg.xcache.Element;

/**
 * 存储器
 * @author enychen Jul 19, 2009
 */
public interface Store {

    /**
     * 把缓存元素放入存储器
     * @param element 缓存元素
     */
    void put(Element element);

    /**
     * 删除存储器中的缓存元素
     * @param key 缓存键
     */
    void delete(Object key);

    /**
     * 获取存储器中的缓存元素
     * @param key 缓存键
     * @return 缓存元素
     */
    Element get(Object key);

    /**
     * 获取所有缓存元素
     * @return 缓存元素Collection
     */
    Collection<Element> getAll();

    /**
     * 清除缓存
     */
    void clear();

    /**
     * 获取缓存元素数量
     * @return 缓存元素数量
     */
    int getAmount();

}
