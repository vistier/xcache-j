package org.wg.xio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import org.wg.xio.command.TestRequest;

/**
 * 测试客户端
 * @author enychen Oct 25, 2009
 */
public class Client {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 1234);
        socketChannel.connect(address);
        socketChannel.configureBlocking(false);

        for (int i = 0; i < 100000000; i++) {
            TestRequest test = new TestRequest();
            test.setId((short) (100 + i));
            test.setCommandId((short) (1000 + i));
            test.setTest("123哈哈！abc第一次测试！");
            socketChannel.write(test.encode());
            
            Thread.sleep(100);
        }

        socketChannel.close();
    }

}
