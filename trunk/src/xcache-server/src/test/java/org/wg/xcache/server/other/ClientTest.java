package org.wg.xcache.server.other;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xcache.protocol.message.PutObjectRequest;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;
import org.wg.xio.ex.LengthMessageHandler;
import org.wg.xio.ex.command.CommandConnector;

/**
 * 测试客户端
 * @author enychen Nov 3, 2009
 */
public class ClientTest {

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setSocketHandlerCount(2);

        LengthMessageHandler lengthMessageHandler = new LengthMessageHandler();

        Executor executor = Executors.newCachedThreadPool();

        Supporter supporter = new Supporter();
        supporter.setConfig(config);
        supporter.setMessageHandler(lengthMessageHandler);
        supporter.setExecutor(executor);

        CommandConnector commandConnector = new CommandConnector(supporter);
        commandConnector.connect();
        commandConnector.connect();
        commandConnector.connect();
        commandConnector.connect();
        commandConnector.connect();

        for (int i = 0; i < 100000000; i++) {
            TestObject testObject = new TestObject();
            testObject.setField1(i);
            testObject.setField2(100 + i);
            testObject.setField3(100L);
            testObject.setField4(10000L);
            testObject.setField5("test" + i);
            testObject.setField6("test__" + i);
            testObject.setField7(false);
            testObject.setField8(true);
            
            PutObjectRequest putObjectRequest = new PutObjectRequest();
            putObjectRequest.setCacheName("test1");
            putObjectRequest.setId(i);
            putObjectRequest.setCommandId(i);
            putObjectRequest.setKey("test" + i);
            putObjectRequest.setObject(testObject);
            putObjectRequest.setLiveTime(10000000);
            putObjectRequest.setIdleTime(10000000);

            commandConnector.send(putObjectRequest);

            Thread.sleep(100);
        }
    }
    
}
