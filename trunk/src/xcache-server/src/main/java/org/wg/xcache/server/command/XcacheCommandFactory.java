package org.wg.xcache.server.command;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.wg.xio.ex.command.Command;
import org.wg.xio.ex.command.CommandFactory;
import org.wg.xio.ex.command.CommandRequest;

/**
 * Xcahce命令工厂
 * @author enychen Nov 3, 2009
 */
public class XcacheCommandFactory implements CommandFactory, BeanFactoryAware {

    /** Xcahce命令Bean的前缀 */
    public static final String XCACHE_COMMAND_BEAN_PREFIX = "xcacheCommand_";

    /** Bean工厂 */
    private BeanFactory        beanFactory;

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.CommandFactory#getCommand(org.wg.xio.ex.command.CommandRequest)
     */
    public Command getCommand(CommandRequest commandRequest) {
        Command command = (Command) this.beanFactory.getBean(XCACHE_COMMAND_BEAN_PREFIX
                + commandRequest.getCommandId());

        return command;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
     */
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
