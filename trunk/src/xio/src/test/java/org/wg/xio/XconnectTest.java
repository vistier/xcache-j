package org.wg.xio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xio.command.TestRequest;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;

/**
 * xconnect测试
 * @author enychen Oct 27, 2009
 */
public class XconnectTest {

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setSocketHandlerCount(2);
        config.setSync(true);
        
        Executor executor = Executors.newCachedThreadPool();

        Supporter supporter = new Supporter();
        supporter.setConfig(config);
        supporter.setExecutor(executor);
        
        Xconnector xconnector = new Xconnector(supporter);
        xconnector.connect();
        
        for (int i = 0; i < 100000000; i++) {
            TestRequest test = new TestRequest();
            test.setId((short) (100 + i));
            test.setCommandId((short) (1000 + i));
            test.setTest("123哈哈！abc第一次测试！");
            xconnector.send(test);

            Thread.sleep(100);
        }
    }

}
