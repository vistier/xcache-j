package org.wg.xserver;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import org.wg.xserver.command.CommandMessage;

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

        for (int i = 0; i < 10; i++) {
            // ByteBuffer bb = ByteBuffer.wrap(("test" + i + "\n").getBytes());

            CommandMessage test = new CommandMessage();
            test.setId((short) (100 + i));
            test.setCommandId((short) (1000 + i));
            socketChannel.write(test.encode());
        }

        socketChannel.close();
    }

}
