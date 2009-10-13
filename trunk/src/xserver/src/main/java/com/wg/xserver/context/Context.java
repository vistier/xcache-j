package com.wg.xserver.context;

import java.nio.ByteBuffer;
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

    /** 已经收到消息的队列 */
    private Queue<ByteBuffer> receivedMessageQueue = new ConcurrentLinkedQueue<ByteBuffer>();

    /** 将要发送消息的队列 */
    private Queue<ByteBuffer> sendingMessageQueue  = new ConcurrentLinkedQueue<ByteBuffer>();

    /**
     * 接收消息
     * @param message 消息
     */
    public void receive(ByteBuffer message) {
        this.receivedMessageQueue.add(message);
    }
    
    /**
     * 发送消息
     * @param message 消息
     */
    public void send(ByteBuffer message) {
        this.sendingMessageQueue.add(message);
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
     * @param serverSupporter 服务器支持者
     */
    public void setServerSupporter(ServerSupporter serverSupporter) {
        this.serverSupporter = serverSupporter;
    }

}
