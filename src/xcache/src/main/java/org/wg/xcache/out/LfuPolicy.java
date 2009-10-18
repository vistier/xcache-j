package com.wg.xcache.out;

import com.wg.xcache.Element;

/**
 * 最不经常使用
 * @author enychen Aug 10, 2009
 */
public class LfuPolicy extends AbstractOutPolicy {

    /*
     * (non-Javadoc)
     * @see com.wg.xcache.out.AbstractOutPolicy#compare(com.wg.xcache.Element,
     *      com.wg.xcache.Element)
     */
    @Override
    public Element compare(Element element1, Element element2) {
        return (element1.getAccessTimes() <= element2.getAccessTimes()) ? element1 : element2;
    }

}
