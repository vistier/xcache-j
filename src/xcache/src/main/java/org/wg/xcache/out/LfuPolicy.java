package org.wg.xcache.out;

import org.wg.xcache.Element;

/**
 * 最不经常使用
 * @author enychen Aug 10, 2009
 */
public class LfuPolicy extends AbstractOutPolicy {

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.out.AbstractOutPolicy#compare(org.wg.xcache.Element,
     *      org.wg.xcache.Element)
     */
    @Override
    public Element compare(Element element1, Element element2) {
        return (element1.getAccessTimes() <= element2.getAccessTimes()) ? element1 : element2;
    }

}
