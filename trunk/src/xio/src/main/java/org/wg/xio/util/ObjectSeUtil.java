package org.wg.xio.util;

/**
 * 对象序列化工具
 * @author enychen Nov 10, 2009
 */
public interface ObjectSeUtil {

    /**
     * 序列化
     * @param object 对象
     * @return 对象字节
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     * @param bytes 对象字节
     * @return 对象
     */
    Object deserialize(byte[] bytes);

}
