package org.wg.xio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private static final Log      log               = LogFactory.getLog(Xconnector.class);

    /** 操作最大次数 */
    private static int            OP_MAX            = Integer.MAX_VALUE - 10000;

    /** socket通道池，相当于连接池 */
    protected List<SocketChannel> socketChannelPool = new CopyOnWriteArrayList<SocketChannel>();

    /** socket通道数量 */
    protected int                 socketChannelCount;

    /** 支持者 */
    protected Supporter           supporter;

    /** IP地址 */
    protected String              ip;

    /** 端口 */
    protected int                 port;

    /** Socket处理器 */
    protected SocketHandler[]     socketHandlers;

    /** socket处理器数量 */
    protected int                 socketHandlerCount;

    /** 连接次数 */
    protected int                 connectTimes;

    /** 操作次数 */
    protected int                 opTimes;

    /**
     * 创建X连接器
     * @param supporter 支持者
     */
    public Xconnector(Supporter supporter) {
        this.supporter = supporter;
        this.ip = supporter.getConfig().getIp();
        this.port = supporter.getConfig().getPort();
        this.socketHandlerCount = supporter.getConfig().getSocketHandlerCount();
        this.socketHandlers = new SocketHandler[this.socketHandlerCount];

        for (int i = 0; i < this.socketHandlerCount; i++) {
            this.socketHandlers[i] = new SocketHandler(supporter);
        }
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

            // 对socket负载均衡处理
            this.socketHandlers[this.connectTimes++ % this.socketHandlerCount].bind(socketChannel);
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

        this.socketChannelPool.add(socketChannel);
        this.socketChannelCount++;

        return true;
    }

    /**
     * 获取一个上下文
     * @return 上下文
     */
    protected Context getOneContext() {
        Context context = null;

        if (this.opTimes > OP_MAX) {
            this.opTimes = OP_MAX % this.socketChannelCount;
        }

        // --轮询socket通道，找到关联的上下文
        SocketChannel socketChannel = this.socketChannelPool.get(this.opTimes++ % this.socketChannelCount);

        for (SocketHandler socketHandler : this.socketHandlers) {
            context = socketHandler.getContext(socketChannel);

            if (context != null) {
                break;
            }
        }

        return context;
    }

    /**
     * 发送请求
     * @param request 请求消息
     * @return 上下文
     */
    public Context send(ByteBuffer request) {
        Context context = this.getOneContext();
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
     * @param context 上下文
     * @param timeOut 超时
     * @return 响应消息
     */
    public ByteBuffer read(Context context, int timeOut) {
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
