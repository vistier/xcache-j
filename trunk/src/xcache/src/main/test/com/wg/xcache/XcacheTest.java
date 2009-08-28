package com.wg.xcache;

import junit.framework.TestCase;

/**
 * Xcache测试
 * @author enychen Aug 28, 2009
 */
public class XcacheTest extends TestCase {

    private Cache cache = XcacheManager.getInstance().getCache("default");

    @Override
    protected void tearDown() throws Exception {
        this.cache.clear();
    }

    /**
     * 测试放入元素
     */
    public void testPut() {
        Element element1 = new Element("key", "value");
        this.cache.put(element1);

        Element element2 = this.cache.get("key");

        assertSame(element1, element2);
    }

    /**
     * 测试更新元素
     */
    public void testPutUpdate() {
        Element element1 = new Element("key", "value1");
        this.cache.put(element1);

        Element element2 = new Element("key", "value2");
        this.cache.put(element2);

        Element element3 = this.cache.get("key");

        assertEquals("value2", element3.getObject());
    }

    /**
     * 测试抛弃元素
     */
    public void testPutAbandon() {
        for (int i = 1; i <= 11; i++) {
            Element element = new Element("key" + i, "value" + i);
            this.cache.put(element);
        }

        CacheStat cacheStat = this.cache.getCacheStat();

        assertEquals(10, cacheStat.getAmount());

        Element element11 = this.cache.get("key11");

        assertNull(element11);
    }

    /**
     * 测试退出
     */
    public void testPutOut() {
        Cache cacheTest1 = XcacheManager.getInstance().getCache("test1");

        for (int i = 1; i <= 10; i++) {
            Element element = new Element("key" + i, "value" + i);
            cacheTest1.put(element);

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                fail();
            }
        }

        Element element11 = new Element("key11", "value11");
        cacheTest1.put(element11);

        Element element1 = cacheTest1.get("key1");

        assertNull(element1);

        Element element11_1 = cacheTest1.get("key11");

        assertNotNull(element11_1);
    }

    /**
     * 测试删除元素
     */
    public void testDelete() {
        for (int i = 1; i <= 10; i++) {
            Element element = new Element("key" + i, "value" + i);
            this.cache.put(element);
        }

        this.cache.delete("key5");

        CacheStat cacheStat = this.cache.getCacheStat();

        assertEquals(9, cacheStat.getAmount());

        Element element5 = this.cache.get("key5");

        assertNull(element5);
    }

    /**
     * 测试获取过期元素
     */
    public void testGetExpire() {
        Element element1 = new Element("key", "value", 100L, 0L);
        this.cache.put(element1);

        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }

        Element element2 = this.cache.get("key");

        assertNull(element2);

        CacheStat cacheStat = this.cache.getCacheStat();

        assertEquals(0, cacheStat.getAmount());
    }

    /**
     * 测试删除过期元素
     */
    public void testDeleteExpireElement() {
        for (int i = 1; i <= 5; i++) {
            Element element = new Element("key" + i, "value" + i, 100L, 0L);
            this.cache.put(element);
        }

        for (int i = 6; i <= 10; i++) {
            Element element = new Element("key" + i, "value" + i);
            this.cache.put(element);
        }

        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }

        int count = this.cache.deleteExpireElement();

        assertEquals(5, count);
    }

}
