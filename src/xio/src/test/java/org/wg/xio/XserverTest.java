package org.wg.xio;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xio.Xserver;
import org.wg.xio.command.TestCommandFactory;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;
import org.wg.xio.ex.command.CommandHandler;

/**
 * xserver测试
 * @author enychen Oct 12, 2009
 */
public class XserverTest {

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setSocketHandlerCount(10);

        TestCommandFactory testCommandFactory = new TestCommandFactory();
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.setCommandFactory(testCommandFactory);

        //SimpleTelnet simpleTelnet = new SimpleTelnet();

        Executor executor = Executors.newCachedThreadPool();

        Supporter supporter = new Supporter();
        supporter.setConfig(config);
        supporter.setMessageHandler(commandHandler);
        //supporter.setMessageHandler(simpleTelnet);
        supporter.setExecutor(executor);

        Xserver xserver = new Xserver(supporter);
        xserver.start();
    }

}
