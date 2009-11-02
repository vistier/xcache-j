package org.wg.xcache.server.other;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xcache.Cache;
import org.wg.xcache.Element;
import org.wg.xcache.XcacheManager;
import org.wg.xcache.protocol.message.PutObjectRequest;
import org.wg.xio.context.Context;
import org.wg.xio.ex.command.Command;
import org.wg.xio.ex.command.CommandRequest;

/**
 * 把对象放入缓存
 * @author enychen Nov 3, 2009
 */
public class PutObject implements Command {

    /** log */
    private static final Log log = LogFactory.getLog(PutObject.class);

    /*
     * (non-Javadoc)
     * @see org.wg.xio.ex.command.Command#execute(org.wg.xio.ex.command.CommandRequest,
     *      org.wg.xio.context.Context)
     */
    public void execute(CommandRequest commandRequest, Context context) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(commandRequest);

        log.info("收到放入缓存请求，length=" + putObjectRequest.getLength() + ", key="
                + putObjectRequest.getKey());

        Element element = new Element(putObjectRequest.getKey(), putObjectRequest.getObject(),
                putObjectRequest.getLiveTime(), putObjectRequest.getIdleTime());

        Cache cache = XcacheManager.getInstance().getCache(putObjectRequest.getCacheName());
        cache.put(element);
    }

}
