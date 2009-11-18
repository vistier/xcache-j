package org.wg.xcache.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Xcache服务器
 * @author enychen Sep 6, 2009
 */
public class XcacheServer {

    /**
     * 程序主方法
     * @param args 参数
     */
    public static void main(String[] args) {
        XcacheServer xcacheServer = new XcacheServer();
        xcacheServer.start();
    }

    /**
     * 启动服务器
     */
    public void start() {
        new ClassPathXmlApplicationContext(new String[] { "xcache-server-ex.xml" });
    }

}
