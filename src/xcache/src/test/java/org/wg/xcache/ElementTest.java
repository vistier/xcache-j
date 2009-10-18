package org.wg.xcache;

import junit.framework.TestCase;

/**
 * 缓存元素测试
 * @author enychen Aug 29, 2009
 */
public class ElementTest extends TestCase {

    /**
     * 测试生存时间过期
     */
    public void testIsExpireLiveTime() {
        Element element = new Element("key", "value", 100L, 0L);
        
        assertFalse(element.isExpire());
        
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }
        
        assertTrue(element.isExpire());
    }
    
    /**
     * 测试空闲时间过期
     */
    public void testIsExpireIdleTime() {
        Element element = new Element("key", "value", 0L, 100L);
        
        assertFalse(element.isExpire());
        
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }
        
        assertTrue(element.isExpire());
        
        element.refreshAccessStat();
        
        assertFalse(element.isExpire());
        
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }
        
        assertTrue(element.isExpire());
    }

}
