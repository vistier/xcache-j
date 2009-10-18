package org.wg.xserver.context;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 上下文
 * @author enychen Oct 11, 2009
 */
public class Context {

    /** socket通道 */
    private SocketChannel     socketChannel;

    /** 服务器支持者 */
    private ServerSupporter   serverSupporter;

    /** 选择键 */
    private SelectionKey      key;

    /** 已经收到消息的队列 */
    private Queue<ByteBuffer> receivedMessageQueue = new ConcurrentLinkedQueue<ByteBuffer>();

    /** 将要发送消息的队列 */
    private Queue<ByteBuffer> sendingMessageQueue  = new ConcurrentLinkedQueue<ByteBuffer>();

    /**
     * 接收消息
     * 
     * @param message 消息
     */
    public void receive(ByteBuffer message) {
        this.receivedMessageQueue.add(message);
    }

    /**
     * 发送消息
     * 
     * @param message 消息
     */
    public void send(ByteBuffer message) {
        this.sendingMessageQueue.add(message);
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
     * 
     * @param serverSupporter 服务器支持者
     */
    public void setServerSupporter(ServerSupporter serverSupporter) {
        this.serverSupporter = serverSupporter;
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

}
