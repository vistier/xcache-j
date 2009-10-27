package org.wg.xio;

import java.nio.channels.SocketChannel;

import org.wg.xio.config.ConnectorConfig;

/**
 * 连接器
 * @author enychen Oct 26, 2009
 */
public class Connector {

    /** 连接器配置 */
    protected ConnectorConfig connectorConfig;

    /** socket通道 */
    protected SocketChannel   socketChannel;

    /** 是否已经连接 */
    protected boolean         connected;

    /** 是否在使用 */
    protected boolean         used;

    /**
     * 连接服务器
     * @return 连接是否成功
     */
    public boolean connect() {
        return false;
    }

    /**
     * 发送
     * @param request 请求消息
     * @return 响应消息
     */
    public Message send(Message request) {
        return null;
    }

    /**
     * 获取是否已经连接
     * @return 是否已经连接
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * 获取是否在使用
     * @return 是否在使用
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * 获取是否可用
     * @return 是否可用
     */
    public boolean isAvailable() {
        return this.connected && !this.used;
    }

}
