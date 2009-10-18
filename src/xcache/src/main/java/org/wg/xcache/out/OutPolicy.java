package org.wg.xcache.out;

import org.wg.xcache.Element;
import org.wg.xcache.store.Store;

/**
 * 退出策略
 * @author enychen Aug 10, 2009
 */
public interface OutPolicy {

    /**
     * 从存储器中选举一个元素退出
     * @param store 存储器
     * @return 退出元素
     */
    Element vote(Store store);

}
