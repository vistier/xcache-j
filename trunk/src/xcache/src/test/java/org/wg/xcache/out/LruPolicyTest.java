package org.wg.xcache.out;

import com.wg.xcache.Element;

import junit.framework.TestCase;

/**
 * 最近最久未使用测试
 * @author enychen Aug 30, 2009
 */
public class LruPolicyTest extends TestCase {

    /**
     * 测试比较
     */
    public void testCompare() {
        LruPolicy lruPolicy = new LruPolicy();
        
        Element element1 = new Element("key", "value");

        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }

        Element element2 = new Element("key", "value");
        
        Element element3 = lruPolicy.compare(element1, element2);
        
        assertSame(element1, element3);
        
        Element element4 = lruPolicy.compare(element2, element1);
        
        assertSame(element1, element4);
        
        //-------------------------------------------------------
        
        element1.refreshAccessStat();
        
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }
        
        element2.refreshAccessStat();
        
        Element element5 = lruPolicy.compare(element1, element2);
        
        assertSame(element1, element5);
        
        Element element6 = lruPolicy.compare(element2, element1);
        
        assertSame(element1, element6);
    }
    
}
