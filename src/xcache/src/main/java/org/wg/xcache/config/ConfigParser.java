package com.wg.xcache.config;

/**
 * 配置解析器
 * @author enychen Aug 9, 2009
 */
public interface ConfigParser {

    /**
     * 解析
     * @return 配置
     */
    Config parse();

}