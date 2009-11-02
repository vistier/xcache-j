package org.wg.xcache.protocol;

/**
 * Xcache命令
 * @author enychen Nov 3, 2009
 */
public class XcacheCommand {

    /** 把对象放入缓存 */
    public static final XcacheCommand PUT_OBJECT     = new XcacheCommand(100001);

    /** 把缓存中对象删除 */
    public static final XcacheCommand DELETE_OBJECT  = new XcacheCommand(100002);

    /** 获取缓存中对象 */
    public static final XcacheCommand GET_OBJECT     = new XcacheCommand(100003);

    /** 清除缓存 */
    public static final XcacheCommand CLEAR_CACHE    = new XcacheCommand(100004);

    /** 获取缓存统计信息 */
    public static final XcacheCommand GET_CACHE_STAT = new XcacheCommand(100005);

    /** 枚举值 */
    private int                       value;

    /**
     * 创建Xcache命令
     * @param value 枚举值
     */
    private XcacheCommand(int value) {
        this.value = value;
    }

    /**
     * 获取枚举值
     * @return 枚举值
     */
    public int getValue() {
        return value;
    }

}
