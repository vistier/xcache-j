package org.wg.xcache.server.command;

import org.wg.xcache.Cache;
import org.wg.xcache.Element;
import org.wg.xcache.protocol.PutObjectRequest;
import org.wg.xio.context.Context;
import org.wg.xio.ex.command.CommandRequest;

/**
 * 把对象放入缓存
 * @author enychen Nov 12, 2009
 */
public class PutObject extends XcacheCommand {

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.server.command.XcacheCommand#execute(java.lang.Object,
     *      org.wg.xio.ex.command.CommandRequest, org.wg.xio.context.Context)
     */
    @Override
    protected void execute(Object request, CommandRequest commandRequest, Context context) {
        PutObjectRequest putObjectRequest = (PutObjectRequest) request;
        Element element = new Element(putObjectRequest.getKey(), putObjectRequest.getObject(),
                putObjectRequest.getLiveTime(), putObjectRequest.getIdleTime());
        Cache cache = this.xcacheManager.getCache(putObjectRequest.getCacheName());

        cache.put(element);
    }

}
