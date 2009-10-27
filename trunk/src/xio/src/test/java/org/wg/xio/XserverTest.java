package org.wg.xio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xio.Xserver;
import org.wg.xio.command.CommandMessageHandler;
import org.wg.xio.command.TestCommandFactory;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;

/**
 * xserver测试
 * @author enychen Oct 12, 2009
 */
public class XserverTest {

    public static void main(String[] args) {
        Config serverConfig = new Config();
        serverConfig.setPort(1234);
        serverConfig.setSocketHandlerCount(2);

        TestCommandFactory defaultCommandFactory = new TestCommandFactory();
        CommandMessageHandler commandMessageHandler = new CommandMessageHandler();
        commandMessageHandler.setCommandFactory(defaultCommandFactory);

        //SimpleTelnet simpleTelnet = new SimpleTelnet();
        
        Executor executor = Executors.newCachedThreadPool();

        Supporter serverSupporter = new Supporter();
        serverSupporter.setServerConfig(serverConfig);
        serverSupporter.setMessageHandler(commandMessageHandler);
        //serverSupporter.setMessageHandler(simpleTelnet);
        serverSupporter.setExecutor(executor);

        Xserver xio = new Xserver(serverSupporter);
        xio.start();
    }

}
