package org.wg.xio.config;

/**
 * 连接器配置
 * @author enychen Oct 26, 2009
 */
public class ConnectorConfig {

    /** 服务器IP */
    private String  serverIp;

    /** 服务器端口 */
    private int     serverPort;

    /** 是否同步调用 */
    private boolean sync;

    /**
     * 获取服务器IP
     * @return 服务器IP
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * 设置服务器IP
     * @param serverIp 服务器IP
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    /**
     * 获取服务器端口
     * @return 服务器端口
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * 设置服务器端口
     * @param serverPort 服务器端口
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * 获取是否同步调用
     * @return 是否同步调用
     */
    public boolean isSync() {
        return sync;
    }

    /**
     * 设置是否同步调用
     * @param sync 是否同步调用
     */
    public void setSync(boolean sync) {
        this.sync = sync;
    }

}
