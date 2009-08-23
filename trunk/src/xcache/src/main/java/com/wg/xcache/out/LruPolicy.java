package com.wg.xcache.out;

import com.wg.xcache.Element;

/**
 * 最近最久未使用
 * @author enychen Aug 10, 2009
 */
public class LruPolicy extends AbstractOutPolicy {

    /*
     * (non-Javadoc)
     * @see com.wg.xcache.out.AbstractOutPolicy#compare(com.wg.xcache.Element,
     *      com.wg.xcache.Element)
     */
    @Override
    public Element compare(Element element1, Element element2) {
        long now = System.currentTimeMillis();
        long interval1 = (element1.getLastAccessTime() > 0L) ? now - element1.getLastAccessTime()
                : now - element1.getCreatedTime();
        long interval2 = (element2.getLastAccessTime() > 0L) ? now - element2.getLastAccessTime()
                : now - element2.getCreatedTime();

        return (interval1 >= interval2) ? element1 : element2;
    }

}
