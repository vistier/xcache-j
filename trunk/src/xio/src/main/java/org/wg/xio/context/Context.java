package org.wg.xio.context;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.wg.xio.Message;
import org.wg.xio.SocketHandler;
import org.wg.xio.config.Supporter;

/**
 * 上下文
 * @author enychen Oct 11, 2009
 */
public class Context {

    /** socket通道 */
    private SocketChannel     socketChannel;

    /** 主机地址 */
    private String            hostAddress;

    /** 支持者 */
    private Supporter         supporter;

    /** Socket处理器 */
    private SocketHandler     socketHandler;

    /** 选择键 */
    private SelectionKey      key;

    /** 已经收到消息的缓冲区 */
    private ByteBuffer        receivedMessageBuffer;

    /** 已经收到消息的队列 */
    private Queue<ByteBuffer> receivedMessageQueue = new ConcurrentLinkedQueue<ByteBuffer>();

    /** 将要发送消息的队列 */
    private Queue<ByteBuffer> sendingMessageQueue  = new ConcurrentLinkedQueue<ByteBuffer>();

    /** 是否正在写入 */
    private boolean           writing;

    /** 读取锁 */
    private Object            readLock             = new Object();
    
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
     * 写入消息
     * @param message 消息
     */
    public void write(ByteBuffer message) {
        // 放入队列，准备发送
        this.sendingMessageQueue.add(message);

        // --标记写入，选择写入
        if (!this.writing) {
            this.writing = true;

            this.resumeSelectWrite();
        }
    }

    /**
     * 写入消息
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
        this.key.selector().wakeup();
    }

    /**
     * 暂停选择写入
     */
    public void suspendSelectWrite() {
        this.key.interestOps(this.key.interestOps() & ~SelectionKey.OP_WRITE);
    }

    /**
     * 继续选择写入
     */
    public void resumeSelectWrite() {
        this.key.interestOps(this.key.interestOps() | SelectionKey.OP_WRITE);
        this.key.selector().wakeup();
    }

    /**
     * 写入完成
     */
    public void writeFinished() {
        this.writing = false;
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
        this.hostAddress = socketChannel.socket().getRemoteSocketAddress().toString();
    }

    /**
     * 获取主机地址
     * @return 主机地址
     */
    public String getHostAddress() {
        return hostAddress;
    }

    /**
     * 获取支持者
     * @return 支持者
     */
    public Supporter getSupporter() {
        return supporter;
    }

    /**
     * 设置支持者
     * @param supporter 支持者
     */
    public void setSupporter(Supporter supporter) {
        this.supporter = supporter;
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

    /**
     * 获取已经收到消息的队列
     * @return 已经收到消息的队列
     */
    public Queue<ByteBuffer> getReceivedMessageQueue() {
        return receivedMessageQueue;
    }

    /**
     * 获取将要发送消息的队列
     * @return 将要发送消息的队列
     */
    public Queue<ByteBuffer> getSendingMessageQueue() {
        return sendingMessageQueue;
    }

    /**
     * 获取读取锁
     * @return 读取锁
     */
    public Object getReadLock() {
        return readLock;
    }

}
