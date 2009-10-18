package com.wg.xcache.config;

/**
 * 配置工厂
 * @author enychen Aug 9, 2009
 */
public class ConfigFactory {

    /** 配置工厂 */
    private static final ConfigFactory configFactory = new ConfigFactory();

    /**
     * 创建配置工厂
     */
    private ConfigFactory() {
    }

    /**
     * 获取配置工厂实例
     * @return 配置工厂实例
     */
    public static ConfigFactory getInstance() {
        return configFactory;
    }

    /**
     * 获取配置
     * @return 配置
     */
    public Config getConfig() {
        ConfigParser configParser = new PropConfigParser();

        return configParser.parse();
    }

}
