package org.wg.xio.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream s = new ObjectOutputStream(baos);
            s.writeObject(object);
            s.flush();
            s.close();
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bytes;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.util.ObjectSeUtil#deserialize(byte[])
     */
    public Object deserialize(byte[] bytes) {
        Object object = null;
        try {
            ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytes);
            ObjectInputStream objIs = new ObjectInputStream(bytesIn);
            object = objIs.readObject();
            objIs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return object;
    }

}
