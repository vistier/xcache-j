package org.wg.xio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xio.Xserver;
import org.wg.xio.command.CommandMessageHandler;
import org.wg.xio.command.TestCommandFactory;
import org.wg.xio.config.ServerConfig;
import org.wg.xio.context.ServerSupporter;

/**
 * xio测试
 * @author enychen Oct 12, 2009
 */
public class XserverTest {

    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(1234);
        serverConfig.setSocketHandlerCount(2);

        TestCommandFactory defaultCommandFactory = new TestCommandFactory();
        CommandMessageHandler commandMessageHandler = new CommandMessageHandler();
        commandMessageHandler.setCommandFactory(defaultCommandFactory);

        Executor executor = Executors.newCachedThreadPool();

        ServerSupporter serverSupporter = new ServerSupporter();
        serverSupporter.setServerConfig(serverConfig);
        serverSupporter.setMessageHandler(commandMessageHandler);
        serverSupporter.setExecutor(executor);

        Xserver xio = new Xserver(serverSupporter);
        xio.start();
    }

}
