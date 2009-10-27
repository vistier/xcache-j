package org.wg.xio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * X连接器
 * <p>
 * 用于连接服务端进行通讯
 * @author enychen Oct 26, 2009
 */
public class Xconnector {

    /** log */
    private static final Log log = LogFactory.getLog(Xconnector.class);

    /** socket通道 */
    protected SocketChannel  socketChannel;

    /** IP地址 */
    protected String         ip;

    /** 端口 */
    protected int            port;

    /** 是否已经连接 */
    protected boolean        connected;

    /** 是否在使用 */
    protected boolean        used;

    /**
     * 创建X连接器
     * @param connectorConfig 连接器配置
     */
    public Xconnector() {
        // this.connectorConfig = connectorConfig;
        // this.ip = connectorConfig.getServerIp();
        // this.port = connectorConfig.getServerPort();
    }

    /**
     * 连接服务器
     * @return 连接是否成功
     */
    public boolean connect() {
        SocketChannel socketChannel = null;

        try {
            // --连接服务器
            socketChannel = SocketChannel.open();
            InetSocketAddress address = new InetSocketAddress(this.ip, this.port);
            socketChannel.connect(address);
            socketChannel.configureBlocking(false);
        } catch (Exception e) {
            log.error("连接服务器异常！host=" + this.ip + ":" + this.port, e);

            // --连接关闭
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (Exception e1) {
                    log.error("连接关闭异常！host=" + this.ip + ":" + this.port, e1);
                }
            }

            return false;
        }

        if (log.isInfoEnabled()) {
            log.info("连接到服务器" + this.ip + ":" + this.port);
        }

        return true;
    }

    /**
     * 发送
     * @param request 请求消息
     * @return 响应消息
     */
    public ByteBuffer send(ByteBuffer request) {
        ByteBuffer response = null;

        return response;
    }

    /**
     * 发送
     * @param request 请求消息
     * @return 响应消息
     */
    public ByteBuffer send(Message request) {
        return this.send(request.encode());
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
