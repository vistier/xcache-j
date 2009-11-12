package org.wg.xcache.server.command;

import org.wg.xcache.spring.XcacheManager;
import org.wg.xio.context.Context;
import org.wg.xio.ex.command.Command;
import org.wg.xio.ex.command.CommandRequest;
import org.wg.xio.ex.command.SerialObjectRequest;
import org.wg.xio.ex.command.SerialObjectResponse;
import org.wg.xio.util.ObjectSeUtil;

/**
 * Xcache命令
 * @author enychen Nov 12, 2009
 */
public abstract class XcacheCommand implements Command {

    /** Xcache缓存管理器 */
    protected XcacheManager xcacheManager;

    /** 对象序列化工具 */
    protected ObjectSeUtil  objectSeUtil;

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.Command#execute(org.wg.xio.ex.command.CommandRequest,
     *      org.wg.xio.context.Context)
     */
    public void execute(CommandRequest commandRequest, Context context) {
        SerialObjectRequest serialObjectRequest = new SerialObjectRequest(commandRequest);
        Object request = this.objectSeUtil.deserialize(serialObjectRequest.getSerialObject());

        this.execute(request, commandRequest, context);
    }

    /**
     * 执行命令
     * @param request 调用请求
     * @param commandRequest 命令请求
     * @param context 上下文
     */
    protected abstract void execute(Object request, CommandRequest commandRequest, Context context);

    /**
     * 写入响应
     * @param response 返回响应
     * @param commandRequest 命令响应
     * @param context 上下文
     */
    protected void writeResponse(Object response, CommandRequest commandRequest, Context context) {
        SerialObjectResponse serialObjectResponse = new SerialObjectResponse();
        byte[] responseBytes = this.objectSeUtil.serialize(response);
        serialObjectResponse.setId(commandRequest.getId());
        serialObjectResponse.setSerialObject(responseBytes);

        context.write(serialObjectResponse);
    }

    // -- Bean注入

    /**
     * 设置Xcache缓存管理器
     * @param xcacheManager Xcache缓存管理器
     */
    public void setXcacheManager(XcacheManager xcacheManager) {
        this.xcacheManager = xcacheManager;
    }

    /**
     * 设置对象序列化工具
     * @param objectSeUtil 对象序列化工具
     */
    public void setObjectSeUtil(ObjectSeUtil objectSeUtil) {
        this.objectSeUtil = objectSeUtil;
    }

}