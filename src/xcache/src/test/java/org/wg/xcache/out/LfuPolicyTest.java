package org.wg.xcache.out;

import org.wg.xcache.Element;

import junit.framework.TestCase;

/**
 * 最不经常使用测试
 * @author enychen Aug 30, 2009
 */
public class LfuPolicyTest extends TestCase {

    /**
     * 测试比较
     */
    public void testCompare() {
        LfuPolicy lfuPolicy = new LfuPolicy();

        Element element1 = new Element("key", "value");
        element1.refreshAccessStat();

        Element element2 = new Element("key", "value");
        element2.refreshAccessStat();
        element2.refreshAccessStat();

        Element element3 = lfuPolicy.compare(element1, element2);

        assertSame(element1, element3);

        Element element4 = lfuPolicy.compare(element2, element1);

        assertSame(element1, element4);
    }

}
