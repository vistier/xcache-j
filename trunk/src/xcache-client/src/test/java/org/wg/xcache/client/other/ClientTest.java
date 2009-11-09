package org.wg.xcache.client.other;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xcache.protocol.PutObjectRequest;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;
import org.wg.xio.ex.LengthMessageHandler;
import org.wg.xio.ex.command.CommandConnector;
import org.wg.xio.ex.command.SerialObjectRequest;

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

            byte[] testObjectBytes = null;
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream s = new ObjectOutputStream(baos);
                s.writeObject(testObject);
                s.flush();
                s.close();
                baos.flush();
                testObjectBytes = baos.toByteArray();
                baos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            PutObjectRequest putObjectRequest = new PutObjectRequest();
            putObjectRequest.setCacheName("test1");
            putObjectRequest.setKey("test" + i);
            putObjectRequest.setObject(testObjectBytes);
            putObjectRequest.setLiveTime(10000000);
            putObjectRequest.setIdleTime(10000000);

            byte[] putObjectRequestBytes = null;
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream s = new ObjectOutputStream(baos);
                s.writeObject(putObjectRequest);
                s.flush();
                s.close();
                baos.flush();
                putObjectRequestBytes = baos.toByteArray();
                baos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            SerialObjectRequest serialObjectRequest = new SerialObjectRequest();
            serialObjectRequest.setId(i);
            serialObjectRequest.setCommandId(i);
            serialObjectRequest.setSeObject(putObjectRequestBytes);

            commandConnector.send(serialObjectRequest);

            Thread.sleep(100);
        }
    }

}
