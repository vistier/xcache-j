package org.wg.xcache.protocol.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import org.wg.xio.ex.command.CommandRequest;
import org.wg.xio.util.XioConst;

/**
 * 把对象放入缓存请求
 * @author enychen Nov 2, 2009
 */
public class PutObjectRequest extends CommandRequest {

    /** 缓存名 */
    protected String cacheName;

    /** 缓存键 */
    protected String key;

    /** 缓存对象 */
    protected Object object;

    /** 生存时间，单位毫秒 */
    protected int    liveTime;

    /** 空闲时间，单位毫秒 */
    protected int    idleTime;

    /**
     * 创建把对象放入缓存请求
     */
    public PutObjectRequest() {
    }

    /**
     * 创建把对象放入缓存请求
     * @param commandRequest 命令请求
     */
    public PutObjectRequest(CommandRequest commandRequest) {
        this.copy(commandRequest);
        this.decode(commandRequest.getMessage());
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandRequest#encode()
     */
    @Override
    public ByteBuffer encode() {
        byte[] cacheNameBytes = this.cacheName.getBytes();
        int cacheNameLength = cacheNameBytes.length;

        byte[] keyBytes = this.key.getBytes();
        int keyLength = keyBytes.length;

        byte[] objectBytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream s = new ObjectOutputStream(baos);
            s.writeObject(this.object);
            s.flush();
            s.close();
            baos.flush();
            objectBytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int objectLength = objectBytes.length;

        this.length = XioConst.COMMAND_REQUEST_HEADER_LENGTH + XioConst.LENGTH_LENGTH
                + cacheNameLength + XioConst.LENGTH_LENGTH + keyLength + XioConst.LENGTH_LENGTH
                + objectLength + XioConst.LENGTH_LENGTH + XioConst.LENGTH_LENGTH;

        ByteBuffer message = ByteBuffer.allocate(this.length);
        message.putInt(this.length);
        message.putInt(this.id);
        message.putInt(this.commandId);
        message.putInt(cacheNameLength);
        message.put(cacheNameBytes);
        message.putInt(keyLength);
        message.put(keyBytes);
        message.putInt(objectLength);
        message.put(objectBytes);
        message.putInt(this.liveTime);
        message.putInt(this.idleTime);
        message.flip();

        return message;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandRequest#decode(java.nio.ByteBuffer)
     */
    @Override
    public void decode(ByteBuffer message) {
        int cacheNameLength = message.getInt();
        byte[] cacheNameBytes = new byte[cacheNameLength];
        message.get(cacheNameBytes);
        this.cacheName = new String(cacheNameBytes);

        int keyLength = message.getInt();
        byte[] keyBytes = new byte[keyLength];
        message.get(keyBytes);
        this.key = new String(keyBytes);

        int objectLength = message.getInt();
        byte[] objectBytes = new byte[objectLength];
        message.get(objectBytes);

        ByteArrayInputStream bytesIn = null;
        try {
            bytesIn = new ByteArrayInputStream(objectBytes);
            ObjectInputStream objIs = new ObjectInputStream(bytesIn);
            this.object = objIs.readObject();
            objIs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.liveTime = message.getInt();
        this.idleTime = message.getInt();
    }

    /**
     * 获取缓存名
     * @return 缓存名
     */
    public String getCacheName() {
        return cacheName;
    }

    /**
     * 设置缓存名
     * @param cacheName 缓存名
     */
    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * 获取缓存键
     * @return 缓存键
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置缓存键
     * @param key 缓存键
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取缓存对象
     * @return 缓存对象
     */
    public Object getObject() {
        return object;
    }

    /**
     * 设置缓存对象
     * @param object 缓存对象
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * 获取生存时间，单位毫秒
     * @return 生存时间，单位毫秒
     */
    public int getLiveTime() {
        return liveTime;
    }

    /**
     * 设置生存时间，单位毫秒
     * @param liveTime 生存时间，单位毫秒
     */
    public void setLiveTime(int liveTime) {
        this.liveTime = liveTime;
    }

    /**
     * 空闲时间，单位毫秒
     * @return 空闲时间，单位毫秒
     */
    public int getIdleTime() {
        return idleTime;
    }

    /**
     * 设置空闲时间，单位毫秒
     * @param idleTime 空闲时间，单位毫秒
     */
    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

}
