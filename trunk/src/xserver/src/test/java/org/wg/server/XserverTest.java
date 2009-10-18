package org.wg.xserver;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xserver.Xserver;
import org.wg.xserver.command.CommandMessageHandler;
import org.wg.xserver.command.DefaultCommandFactory;
import org.wg.xserver.config.ServerConfig;
import org.wg.xserver.context.ServerSupporter;


/**
 * @author enychen Oct 12, 2009
 */
public class XserverTest {

    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(1234);
        serverConfig.setSocketHandlerCount(2);

        DefaultCommandFactory defaultCommandFactory = new DefaultCommandFactory();
        CommandMessageHandler commandMessageHandler = new CommandMessageHandler();
        commandMessageHandler.setCommandFactory(defaultCommandFactory);

        Executor executor = Executors.newCachedThreadPool();

        ServerSupporter serverSupporter = new ServerSupporter();
        serverSupporter.setServerConfig(serverConfig);
        serverSupporter.setMessageHandler(commandMessageHandler);
        serverSupporter.setExecutor(executor);
        
        Xserver xserver = new Xserver(serverSupporter);
        xserver.start();
    }

}
