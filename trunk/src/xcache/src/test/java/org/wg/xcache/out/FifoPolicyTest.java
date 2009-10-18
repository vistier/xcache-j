package org.wg.xcache.out;

import java.util.ArrayList;
import java.util.Collection;

import com.wg.xcache.Element;

import junit.framework.TestCase;

/**
 * 先进先出测试
 * @author enychen Aug 29, 2009
 */
public class FifoPolicyTest extends TestCase {

    private FifoPolicy fifoPolicy = new FifoPolicy();

    /**
     * 测试比较
     */
    public void testCompare() {
        Element element1 = new Element("key", "value");

        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            fail();
        }

        Element element2 = new Element("key", "value");

        Element element3 = this.fifoPolicy.compare(element1, element2);

        assertSame(element1, element3);

        Element element4 = this.fifoPolicy.compare(element2, element1);

        assertSame(element1, element4);
    }

    /**
     * 测试获取退出元素
     */
    public void testGetOutElement() {
        Element[] candidates = new Element[11];
        
        for (int i = 1; i <= 10; i++) {
            Element element = new Element("key" + i, "value" + i);
            candidates[i] = element;
            
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                fail();
            }
        }
        
        Element element1_1 = this.fifoPolicy.getOutElement(candidates);
        
        assertSame(candidates[1], element1_1);
    }
    
    /**
     * 测试获取候选人
     */
    public void testGetCandidates() {
        Collection<Element> elements = new ArrayList<Element>();
        
        for (int i = 1; i <= 100; i++) {
            Element element = new Element("key" + i, "value" + i);
            elements.add(element);
        }
        
        Element[] candidates = this.fifoPolicy.getCandidates(elements);
        
        assertEquals(10, candidates.length);
    }

}
