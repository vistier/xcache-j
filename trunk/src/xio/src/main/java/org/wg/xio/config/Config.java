package org.wg.xio.config;

/**
 * 服务器配置
 * @author enychen Oct 11, 2009
 */
public class Config {

    /** IP地址 */
    private String  ip                 = "127.0.0.1";

    /** 端口 */
    private int     port               = 1234;

    /** socket处理器数量 */
    private int     SocketHandlerCount = 1;

    /** 缓冲区大小 */
    private int     bufferSize         = 1024;

    /** 是否同步调用 */
    private boolean sync;

    /**
     * 获取IP地址
     * @return IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取端口
     * @return 端口
     */
    public int getPort() {
        return port;
    }

    /**
     * 设置端口
     * @param port 端口
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * 获取socket处理器数量
     * @return socket处理器数量
     */
    public int getSocketHandlerCount() {
        return SocketHandlerCount;
    }

    /**
     * 设置socket处理器数量
     * @param socketHandlerCount socket处理器数量
     */
    public void setSocketHandlerCount(int socketHandlerCount) {
        SocketHandlerCount = socketHandlerCount;
    }

    /**
     * 获取缓冲区大小
     * @return 缓冲区大小
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * 设置缓冲区大小
     * @param bufferSize 缓冲区大小
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * 获取是否同步调用
     * @return 是否同步调用
     */
    public boolean isSync() {
        return sync;
    }

    /**
     * 设置是否同步调用
     * @param sync 是否同步调用
     */
    public void setSync(boolean sync) {
        this.sync = sync;
    }

}
