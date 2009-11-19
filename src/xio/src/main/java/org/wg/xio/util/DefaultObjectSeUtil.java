package org.wg.xio.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.wg.xio.XioException;

/**
 * 默认对象序列化工具
 * @author enychen Nov 10, 2009
 */
public class DefaultObjectSeUtil implements ObjectSeUtil {

    /*
     * (non-Javadoc)
     * @see org.wg.xio.util.ObjectSeUtil#serialize(java.lang.Object)
     */
    public byte[] serialize(Object object) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (Exception e) {
            throw new XioException("使用默认对象序列化工具序列化异常！", e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e) {
                    throw new XioException("使用默认对象序列化工具序列化后，关闭对象输出流异常！", e);
                }
            }
        }

        return bytes;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.util.ObjectSeUtil#deserialize(byte[])
     */
    public Object deserialize(byte[] bytes) {
        Object object = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            object = ois.readObject();
        } catch (Exception e) {
            throw new XioException("使用默认对象序列化工具反序列化异常！", e);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception e) {
                    throw new XioException("使用默认对象序列化工具反序列化后，关闭对象输入流异常！", e);
                }
            }
        }

        return object;
    }

}
