package org.wg.xcache.server.other;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.wg.xio.Xserver;
import org.wg.xio.config.Config;
import org.wg.xio.config.Supporter;
import org.wg.xio.ex.command.CommandHandler;

/**
 * 测试服务器
 * @author enychen Nov 3, 2009
 */
public class ServerTest {

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.setSocketHandlerCount(10);

        XcacheCommandFactory xcacheCommandFactory = new XcacheCommandFactory();
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.setCommandFactory(xcacheCommandFactory);

        Executor executor = Executors.newCachedThreadPool();

        Supporter supporter = new Supporter();
        supporter.setConfig(config);
        supporter.setMessageHandler(commandHandler);
        supporter.setExecutor(executor);

        Xserver xserver = new Xserver(supporter);
        xserver.start();
    }
    
}
