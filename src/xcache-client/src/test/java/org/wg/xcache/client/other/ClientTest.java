package org.wg.xcache.client.other;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xcache.protocol.GetObjectRequest;
import org.wg.xcache.protocol.GetObjectResponse;
import org.wg.xcache.protocol.PutObjectRequest;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;
import org.wg.xio.context.Context;
import org.wg.xio.ex.LengthMessageHandler;
import org.wg.xio.ex.command.CommandConnector;
import org.wg.xio.ex.command.CommandResponse;
import org.wg.xio.ex.command.SerialObjectRequest;
import org.wg.xio.ex.command.SerialObjectResponse;
import org.wg.xio.util.DefaultObjectSeUtil;
import org.wg.xio.util.ObjectSeUtil;

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

        ObjectSeUtil objectSeUtil = new DefaultObjectSeUtil();

        for (int i = 0; i < 100; i++) {
            TestObject testObject = new TestObject();
            testObject.setField1(i);
            testObject.setField2(100 + i);
            testObject.setField3(100L);
            testObject.setField4(10000L);
            testObject.setField5("test" + i);
            testObject.setField6("test__" + i);
            testObject.setField7(false);
            testObject.setField8(true);

            byte[] testObjectBytes = objectSeUtil.serialize(testObject);

            PutObjectRequest putObjectRequest = new PutObjectRequest();
            putObjectRequest.setCacheName("default");
            putObjectRequest.setKey("test" + i);
            putObjectRequest.setObject(testObjectBytes);
            putObjectRequest.setLiveTime(10000000);
            putObjectRequest.setIdleTime(10000000);

            byte[] putObjectRequestBytes = objectSeUtil.serialize(putObjectRequest);

            SerialObjectRequest serialObjectRequest = new SerialObjectRequest();
            serialObjectRequest.setId(i);
            serialObjectRequest.setCommandId(100001);
            serialObjectRequest.setSerialObject(putObjectRequestBytes);

            commandConnector.send(serialObjectRequest);

            Thread.sleep(100);
        }

        for (int i = 0; i < 100; i++) {
            GetObjectRequest getObjectRequest = new GetObjectRequest();
            getObjectRequest.setCacheName("default");
            getObjectRequest.setKey("test" + i);

            SerialObjectRequest serialObjectRequest = new SerialObjectRequest();
            serialObjectRequest.setId(i);
            serialObjectRequest.setCommandId(100003);
            serialObjectRequest.setSerialObject(objectSeUtil.serialize(getObjectRequest));

            Context context = commandConnector.send(serialObjectRequest);
            CommandResponse commandResponse = commandConnector.read(context, i, 1000);
            SerialObjectResponse serialObjectResponse = new SerialObjectResponse(commandResponse);
            GetObjectResponse getObjectResponse = (GetObjectResponse) objectSeUtil
                    .deserialize(serialObjectResponse.getSerialObject());
            TestObject testObject = (TestObject) objectSeUtil.deserialize(getObjectResponse
                    .getObject());
            System.out.println("获取到缓存" + "test" + i + "，对象field1=" + testObject.getField1());
        }
    }

}
