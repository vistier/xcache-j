package org.wg.xio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xio.command.TestRequest;
import org.wg.xio.command.TestResponse;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;
import org.wg.xio.context.Context;
import org.wg.xio.ex.LengthMessageHandler;
import org.wg.xio.ex.command.CommandConnector;
import org.wg.xio.ex.command.CommandResponse;

/**
 * xconnect测试
 * @author enychen Oct 27, 2009
 */
public class XconnectTest {

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setSocketHandlerCount(2);

        LengthMessageHandler lengthMessageHandler = new LengthMessageHandler();

        Executor executor = Executors.newCachedThreadPool();

        Supporter supporter = new Supporter();
        supporter.setConfig(config);
        supporter.setMessageHandler(lengthMessageHandler);
        supporter.setExecutor(executor);

        // Xconnector xconnector = new Xconnector(supporter);
        // xconnector.connect();

        CommandConnector commandConnector = new CommandConnector(supporter);
        commandConnector.connect();
        commandConnector.connect();
        commandConnector.connect();
        commandConnector.connect();
        commandConnector.connect();

        for (int i = 0; i < 100000000; i++) {
            TestRequest test = new TestRequest();
            test.setId((short) (100 + i));
            test.setCommandId((short) (1000 + i));
            test.setTest("123哈哈！abc第一次测试！");

            // xconnector.send(test);
            // ByteBuffer message = xconnector.read(1000);
            // LengthMessage lengthMessage = new LengthMessage();
            // lengthMessage.decode(message);
            //
            // byte[] testBytes = new byte[lengthMessage.getBody().remaining()];
            // lengthMessage.getBody().get(testBytes);
            // System.out.println(new String(testBytes));

            Context context = commandConnector.send(test);
            CommandResponse commandResponse = commandConnector.read(context, test.getId(), 1000);
            TestResponse testResponse = new TestResponse(commandResponse);
            //testResponse.decode(commandResponse.getMessage());
            System.out.println(testResponse.getTest());

            Thread.sleep(100);
        }
    }

}
