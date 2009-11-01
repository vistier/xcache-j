package org.wg.xio.ex.command;

import java.nio.ByteBuffer;

import org.wg.xio.Xconnector;
import org.wg.xio.config.Supporter;
import org.wg.xio.context.Context;

/**
 * 命令连接器
 * <p>
 * 用于连接命令服务器
 * @author enychen Nov 1, 2009
 */
public class CommandConnector extends Xconnector {

    /**
     * 创建命令连接器
     * @param supporter 支持者
     */
    public CommandConnector(Supporter supporter) {
        super(supporter);
    }

    /**
     * 读取响应
     * @param requestId 请求ID
     * @param timeOut 超时
     * @return 命令响应
     */
    public CommandResponse read(int requestId, int timeOut) {
        Context context = this.socketHandler.getContext(this.socketChannel);
        ByteBuffer response = null;
        CommandResponse commandResponse = null;
        CommandResponse tempResponse = new CommandResponse();

        synchronized (context.getReadLock()) {
            // --直到超时用完，一直读取响应
            while (timeOut > 0) {
                response = context.getReceivedMessageQueue().poll();

                // --队列中没有响应时，等待响应
                if (response == null) {
                    try {
                        long before = System.currentTimeMillis();

                        context.getReadLock().wait(timeOut);

                        // 更新超时
                        timeOut -= (int) (System.currentTimeMillis() - before);
                    } catch (InterruptedException e) {
                        break;
                    }
                } else {
                    tempResponse.decode(response);

                    // --如果当前响应的ID和请求ID一致，则当前响应就是要读取的响应
                    if (tempResponse.getId() == requestId) {
                        commandResponse = tempResponse;

                        break;
                    }
                }
            }
        }

        return commandResponse;
    }

    /*
     * (non-Javadoc)
     * @see org.wg.xio.Xconnector#read(int)
     */
    @Override
    @Deprecated
    public ByteBuffer read(int timeOut) {
        throw new UnsupportedOperationException();
    }

}
