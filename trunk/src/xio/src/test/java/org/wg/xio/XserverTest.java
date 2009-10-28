package org.wg.xio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xio.Xserver;
import org.wg.xio.command.TestCommandFactory;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;
import org.wg.xio.ex.command.CommandMessageHandler;

/**
 * xserver测试
 * @author enychen Oct 12, 2009
 */
public class XserverTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.setSocketHandlerCount(10);

        TestCommandFactory defaultCommandFactory = new TestCommandFactory();
        CommandMessageHandler commandMessageHandler = new CommandMessageHandler();
        commandMessageHandler.setCommandFactory(defaultCommandFactory);

        //SimpleTelnet simpleTelnet = new SimpleTelnet();

        Executor executor = Executors.newCachedThreadPool();

        Supporter supporter = new Supporter();
        supporter.setConfig(config);
        supporter.setMessageHandler(commandMessageHandler);
        //supporter.setMessageHandler(simpleTelnet);
        supporter.setExecutor(executor);

        Xserver xserver = new Xserver(supporter);
        xserver.start();
    }

}
