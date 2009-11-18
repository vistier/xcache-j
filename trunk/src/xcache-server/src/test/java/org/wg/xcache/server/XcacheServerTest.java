package org.wg.xcache.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Xcache服务器
 * @author enychen Nov 19, 2009
 */
public class XcacheServerTest {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext(new String[] { "xcache-server-ex.xml" });
    }

}
