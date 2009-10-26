package org.wg.xio.context;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.wg.xio.Message;
import org.wg.xio.SocketHandler;

/**
 * 上下文
 * @author enychen Oct 11, 2009
 */
public class Context {

    /** socket通道 */
    private SocketChannel     socketChannel;

    /** 客户端地址 */
    private String            clientAddress;

    /** 服务器支持者 */
    private ServerSupporter   serverSupporter;

    /** Socket处理器 */
    private SocketHandler     socketHandler;

    /** 选择键 */
    private SelectionKey      key;

    /** 已经收到消息的缓冲区 */
    private ByteBuffer        receivedMessageBuffer;

    /** 将要发送消息的队列 */
    private Queue<ByteBuffer> sendingMessageQueue = new ConcurrentLinkedQueue<ByteBuffer>();

    /** 是否正在回写 */
    private boolean           writing;

    /** 回写锁 */
    private Object            writeLock           = new Object();

    /**
     * 接收消息
     * @param message 消息
     */
    public void receive(ByteBuffer message) {
        // --将消息放入缓冲区
        if (this.receivedMessageBuffer == null) {
            this.receivedMessageBuffer = ByteBuffer.allocate(message.limit());
            this.receivedMessageBuffer.put(message);
        } else {
            ByteBuffer temp = this.receivedMessageBuffer;
            this.receivedMessageBuffer = ByteBuffer.allocate(temp.limit() + message.limit());
            this.receivedMessageBuffer.put(temp).put(message);
        }

        this.receivedMessageBuffer.flip();
    }

    /**
     * 根据长度获取消息
     * @param length 长度
     * @return 消息
     */
    public ByteBuffer getMessageByLength(int length) {
        int limit = this.receivedMessageBuffer.rewind().limit();

        if (length > limit) {
            return null;
        }

        // --从缓冲区获取length消息
        this.receivedMessageBuffer.limit(length);

        ByteBuffer message = ByteBuffer.allocate(length);
        message.put(this.receivedMessageBuffer);
        message.flip();

        // --截掉缓冲区length消息
        ByteBuffer temp = this.receivedMessageBuffer;
        temp.limit(limit);

        this.receivedMessageBuffer = ByteBuffer.allocate(limit - length);
        this.receivedMessageBuffer.put(temp);
        this.receivedMessageBuffer.flip();

        return message;
    }

    /**
     * 回写消息
     * @param message 消息
     */
    public void write(ByteBuffer message) {
        this.sendingMessageQueue.add(message);
    }

    /**
     * 回写消息
     * @param message 消息
     */
    public void write(Message message) {
        this.write(message.encode());
    }

    /**
     * 关闭连接
     */
    public void close() {
        this.socketHandler.close(this);
    }

    /**
     * 暂停选择读取
     */
    public void suspendSelectRead() {
        this.key.interestOps(this.key.interestOps() & ~SelectionKey.OP_READ);
    }

    /**
     * 继续选择读取
     */
    public void resumeSelectRead() {
        this.key.interestOps(this.key.interestOps() | SelectionKey.OP_READ);
    }

    /**
     * 暂停选择回写
     */
    public void suspendSelectWrite() {
        this.key.interestOps(this.key.interestOps() & ~SelectionKey.OP_WRITE);
    }

    /**
     * 继续选择回写
     */
    public void resumeSelectWrite() {
        this.key.interestOps(this.key.interestOps() | SelectionKey.OP_WRITE);
    }

    /**
     * 获取socket通道
     * @return socket通道
     */
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    /**
     * 设置socket通道
     * @param socketChannel socket通道
     */
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.clientAddress = socketChannel.socket().getRemoteSocketAddress().toString();
    }

    /**
     * 获取客户端地址
     * @return 客户端地址
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * 获取服务器支持者
     * @return 服务器支持者
     */
    public ServerSupporter getServerSupporter() {
        return serverSupporter;
    }

    /**
     * 设置服务器支持者
     * @param serverSupporter 服务器支持者
     */
    public void setServerSupporter(ServerSupporter serverSupporter) {
        this.serverSupporter = serverSupporter;
    }

    /**
     * 获取Socket处理器
     * @return Socket处理器
     */
    public SocketHandler getSocketHandler() {
        return socketHandler;
    }

    /**
     * 设置Socket处理器
     * @param socketHandler Socket处理器
     */
    public void setSocketHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    /**
     * 获取选择键
     * @return 选择键
     */
    public SelectionKey getKey() {
        return key;
    }

    /**
     * 设置选择键
     * @param key 选择键
     */
    public void setKey(SelectionKey key) {
        this.key = key;
    }

    /**
     * 获取已经收到消息的缓冲
     * @return 已经收到消息的缓冲
     */
    public ByteBuffer getReceivedMessageBuffer() {
        return receivedMessageBuffer;
    }

    /**
     * 设置已经收到消息的缓冲
     * @param receivedMessageBuffer 已经收到消息的缓冲
     */
    public void setReceivedMessageBuffer(ByteBuffer receivedMessageBuffer) {
        this.receivedMessageBuffer = receivedMessageBuffer;
    }

}
