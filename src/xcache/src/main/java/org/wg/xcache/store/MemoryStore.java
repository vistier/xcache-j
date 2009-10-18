package org.wg.xcache.store;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.wg.xcache.Element;
import org.wg.xcache.util.XcacheConst;

/**
 * 内存
 * @author enychen Jul 19, 2009
 */
public class MemoryStore implements Store {

    /** 存储在ConcurrentHashMap */
    private Map<Object, Element> map = new ConcurrentHashMap<Object, Element>(
                                             XcacheConst.MAP_INIT_CAPACITY,
                                             XcacheConst.MAP_LOAD_FACTOR,
                                             XcacheConst.MAP_CONCURRENCY_LEVEL);

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.store.Store#put(org.wg.xcache.Element)
     */
    public void put(Element element) {
        this.map.put(element.getKey(), element);
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.store.Store#delete(java.lang.Object)
     */
    public void delete(Object key) {
        this.map.remove(key);
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.store.Store#get(java.lang.Object)
     */
    public Element get(Object key) {
        return this.map.get(key);
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.store.Store#getAll()
     */
    public Collection<Element> getAll() {
        return this.map.values();
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.store.Store#clear()
     */
    public void clear() {
        this.map.clear();
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.store.Store#getAmount()
     */
    public int getAmount() {
        return this.map.size();
    }

}
