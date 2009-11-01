package org.wg.xio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xio.config.Supporter;
import org.wg.xio.context.Context;

/**
 * X连接器
 * <p>
 * 用于连接服务端进行通讯
 * @author enychen Oct 26, 2009
 */
public class Xconnector {

    /** log */
    private static final Log log = LogFactory.getLog(Xconnector.class);

    /** 支持者 */
    protected Supporter      supporter;

    /** socket通道 */
    protected SocketChannel  socketChannel;

    /** IP地址 */
    protected String         ip;

    /** 端口 */
    protected int            port;

    /** Socket处理器 */
    protected SocketHandler  socketHandler;

    /**
     * 创建X连接器
     * @param supporter 支持者
     */
    public Xconnector(Supporter supporter) {
        this.supporter = supporter;
        this.ip = supporter.getConfig().getIp();
        this.port = supporter.getConfig().getPort();
        this.socketHandler = new SocketHandler(supporter);
    }

    /**
     * 连接服务器
     * @return 连接是否成功
     */
    public boolean connect() {
        try {
            // --连接服务器
            this.socketChannel = SocketChannel.open();
            InetSocketAddress address = new InetSocketAddress(this.ip, this.port);
            this.socketChannel.connect(address);
            this.socketHandler.bind(this.socketChannel);
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
     * 发送请求
     * @param request 请求消息
     * @return 上下文
     */
    public Context send(ByteBuffer request) {
        Context context = this.socketHandler.getContext(this.socketChannel);
        context.write(request);

        return context;
    }

    /**
     * 发送请求
     * @param request 请求消息
     * @return 上下文
     */
    public Context send(Message request) {
        return this.send(request.encode());
    }

    /**
     * 读取响应
     * @param timeOut 超时
     * @return 响应消息
     */
    public ByteBuffer read(int timeOut) {
        Context context = this.socketHandler.getContext(this.socketChannel);
        ByteBuffer response = null;

        synchronized (context.getReadLock()) {
            response = context.getReceivedMessageQueue().poll();

            // --队列中没有响应时，等待响应
            if (response == null) {
                try {
                    context.getReadLock().wait(timeOut);
                } catch (InterruptedException e) {
                }

                response = context.getReceivedMessageQueue().poll();
            }
        }

        return response;
    }

}
