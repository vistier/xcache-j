package org.wg.xcache.store;

/**
 * 存储媒介
 * @author enychen Jul 19, 2009
 */
public class StoreMedia {

    /** 内存 */
    public static final StoreMedia   MEMORY = new StoreMedia("MEMORY");

    /** 存储媒介列表 */
    public static final StoreMedia[] VALUES = { MEMORY };

    /** 枚举值 */
    private String                   value;

    /**
     * 创建存储媒介
     * @param value 枚举值
     */
    private StoreMedia(String value) {
        this.value = value;
    }

    /**
     * 根据枚举值获取存储媒介
     * @param value 枚举值
     * @return 存储媒介
     */
    public static StoreMedia valueOf(String value) {
        for (StoreMedia storeMedia : VALUES) {
            if (storeMedia.value.equals(value)) {
                return storeMedia;
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
