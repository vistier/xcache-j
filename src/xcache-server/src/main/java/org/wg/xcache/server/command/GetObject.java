package org.wg.xcache.server.command;

import org.wg.xcache.Cache;
import org.wg.xcache.Element;
import org.wg.xcache.protocol.GetObjectRequest;
import org.wg.xcache.protocol.GetObjectResponse;
import org.wg.xio.context.Context;
import org.wg.xio.ex.command.CommandRequest;

/**
 * 获取缓存中对象
 * @author enychen Nov 12, 2009
 */
public class GetObject extends XcacheCommand {

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.server.command.XcacheCommand#execute(java.lang.Object,
     *      org.wg.xio.ex.command.CommandRequest, org.wg.xio.context.Context)
     */
    @Override
    protected void execute(Object request, CommandRequest commandRequest, Context context) {
        GetObjectRequest getObjectRequest = (GetObjectRequest) request;
        Cache cache = this.xcacheManager.getCache(getObjectRequest.getCacheName());
        
        Element element = cache.get(getObjectRequest.getKey());
        GetObjectResponse getObjectResponse = new GetObjectResponse();

        if (element != null) {
            getObjectResponse.setObject((byte[]) element.getObject());
        }

        this.writeResponse(getObjectResponse, commandRequest, context);
    }

}
