package org.wg.xcache.protocol;

import java.io.Serializable;

/**
 * 获取缓存中对象响应
 * @author enychen Nov 12, 2009
 */
public class GetObjectResponse implements Serializable {

    /** serialVersionUID = 732176813078193718L */
    private static final long serialVersionUID = 732176813078193718L;

    /** 缓存对象 */
    protected byte[]          object;

    /**
     * 获取缓存对象
     * @return 缓存对象
     */
    public byte[] getObject() {
        return object;
    }

    /**
     * 设置缓存对象
     * @param object 缓存对象
     */
    public void setObject(byte[] object) {
        this.object = object;
    }

}
