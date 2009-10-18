package org.wg.xcache.server.config;

/**
 * 配置解析器
 * @author enychen Sep 6, 2009
 */
public interface ConfigParser {

    /**
     * 解析
     * @return 服务器配置
     */
    ServerConfig parse();

}
