package com.wg.xcache.out;

import java.util.HashMap;
import java.util.Map;

/**
 * 退出策略工厂
 * @author enychen Aug 10, 2009
 */
public class OutPolicyFactory {

    /** 退出策略工厂 */
    private static final OutPolicyFactory outPolicyFactory = new OutPolicyFactory();

    /** 退出策略Map */
    private Map<OutPolicyType, OutPolicy> outPolicyMap     = new HashMap<OutPolicyType, OutPolicy>();

    /**
     * 创建退出策略工厂
     */
    private OutPolicyFactory() {
        this.outPolicyMap.put(OutPolicyType.FIFO, new FifoPolicy());
        this.outPolicyMap.put(OutPolicyType.LFU, new LfuPolicy());
        this.outPolicyMap.put(OutPolicyType.LRU, new LruPolicy());
    }

    /**
     * 获取退出策略工厂实例
     * @return 退出策略工厂实例
     */
    public static OutPolicyFactory getInstance() {
        return outPolicyFactory;
    }

    /**
     * 获取退出策略
     * @param outPolicyType 退出策略类型
     * @return 退出策略
     */
    public OutPolicy getOutPolicy(OutPolicyType outPolicyType) {
        return this.outPolicyMap.get(outPolicyType);
    }

}
