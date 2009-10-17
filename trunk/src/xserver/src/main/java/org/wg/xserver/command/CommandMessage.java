package org.wg.xserver.command;

import java.nio.ByteBuffer;

/**
 * 命令消息
 * @author enychen Oct 11, 2009
 */
public class CommandMessage {

    /** 消息ID */
    private int        id;

    /** 命令ID */
    private int        commandId;

    /** 消息 */
    private ByteBuffer message;

    /**
     * 创建命令消息
     * @param message 消息
     */
    public CommandMessage(ByteBuffer message) {
        this.message = message;
    }

    /**
     * 获取消息ID
     * @return 消息ID
     */
    public int getId() {
        return id;
    }

    /**
     * 获取命令ID
     * @return 命令ID
     */
    public int getCommandId() {
        return commandId;
    }

    /**
     * 获取消息
     * @return 消息
     */
    public ByteBuffer getMessage() {
        return message;
    }

}
