package com.wg.xserver.config;

/**
 * 服务器配置
 * @author enychen Oct 11, 2009
 */
public class ServerConfig {

    /** 端口 */
    private int port               = 1234;

    /** socket处理器数量 */
    private int SocketHandlerCount = 1;

    /** 缓冲区大小 */
    private int bufferSize         = 1024;

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

}
