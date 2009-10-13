package com.wg.xserver;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.wg.xserver.command.CommandMessageHandler;
import com.wg.xserver.command.DefaultCommandFactory;
import com.wg.xserver.config.ServerConfig;
import com.wg.xserver.context.ServerSupporter;

/**
 * @author enychen Oct 12, 2009
 */
public class XserverTest {

    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(1234);

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
