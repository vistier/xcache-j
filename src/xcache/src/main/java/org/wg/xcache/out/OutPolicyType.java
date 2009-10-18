package org.wg.xcache.out;

/**
 * 退出策略类型
 * @author enychen Aug 10, 2009
 */
public class OutPolicyType {

    /** 先进先出 */
    public static final OutPolicyType   FIFO   = new OutPolicyType("FIFO");

    /** 最不经常使用 */
    public static final OutPolicyType   LFU    = new OutPolicyType("LFU");

    /** 最近最久未使用 */
    public static final OutPolicyType   LRU    = new OutPolicyType("LRU");

    /** 退出策略类型列表 */
    public static final OutPolicyType[] VALUES = { FIFO, LFU, LRU };

    /** 枚举值 */
    private String                      value;

    /**
     * 创建退出策略类型
     * @param value 枚举值
     */
    private OutPolicyType(String value) {
        this.value = value;
    }

    /**
     * 根据枚举值获取退出策略类型
     * @param value 枚举值
     * @return 退出策略类型
     */
    public static OutPolicyType valueOf(String value) {
        for (OutPolicyType outPolicyType : VALUES) {
            if (outPolicyType.value.equals(value)) {
                return outPolicyType;
            }
        }

        return null;
    }

    /**
     * 获取枚举值
     * @return 枚举值
     */
    public String getValue() {
        return value;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return value;
    }

}
