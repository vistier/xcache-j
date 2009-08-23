package com.wg.xcache.store;

/**
 * 存储器工厂，专门制造存储器
 * @author enychen Jul 30, 2009
 */
public class StoreFactory {

    /** 存储器工厂 */
    private static final StoreFactory storeFactory = new StoreFactory();

    /**
     * 创建存储器工厂
     */
    private StoreFactory() {
    }

    /**
     * 获取存储器工厂实例
     * @return 存储器工厂实例
     */
    public static StoreFactory getInstance() {
        return storeFactory;
    }

    /**
     * 获取存储器
     * @param storeMedia 存储媒介
     * @return 存储器
     */
    public Store getStore(StoreMedia storeMedia) {
        Store store = null;

        if (storeMedia == StoreMedia.MEMORY) {
            store = new MemoryStore();
        }

        return store;
    }

}
