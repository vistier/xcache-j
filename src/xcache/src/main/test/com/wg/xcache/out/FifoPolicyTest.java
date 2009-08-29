package com.wg.xcache.out;

import com.wg.xcache.Element;

import junit.framework.TestCase;

/**
 * 先进先出测试
 * @author enychen Aug 29, 2009
 */
public class FifoPolicyTest extends TestCase {

    /**
     * 测试比较
     */
    public void testCompare() {
        FifoPolicy fifoPolicy = new FifoPolicy();

        Element element1 = new Element("key", "value");

        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }

        Element element2 = new Element("key", "value");

        Element element3 = fifoPolicy.compare(element1, element2);

        assertSame(element1, element3);

        Element element4 = fifoPolicy.compare(element2, element1);

        assertSame(element1, element4);
    }

}
