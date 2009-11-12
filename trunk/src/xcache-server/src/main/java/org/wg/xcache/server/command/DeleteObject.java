package org.wg.xcache.server.command;

import org.wg.xcache.Cache;
import org.wg.xcache.protocol.DeleteObjectRequest;
import org.wg.xio.context.Context;
import org.wg.xio.ex.command.CommandRequest;

/**
 * 把缓存中对象删除
 * @author enychen Nov 12, 2009
 */
public class DeleteObject extends XcacheCommand {

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.server.command.XcacheCommand#execute(java.lang.Object,
     *      org.wg.xio.ex.command.CommandRequest, org.wg.xio.context.Context)
     */
    @Override
    protected void execute(Object request, CommandRequest commandRequest, Context context) {
        DeleteObjectRequest deleteObjectRequest = (DeleteObjectRequest) request;
        Cache cache = this.xcacheManager.getCache(deleteObjectRequest.getCacheName());

        cache.delete(deleteObjectRequest.getKey());
    }

}
