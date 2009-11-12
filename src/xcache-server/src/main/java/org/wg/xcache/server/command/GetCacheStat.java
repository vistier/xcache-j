package org.wg.xcache.server.command;

import org.wg.xcache.Cache;
import org.wg.xcache.CacheStat;
import org.wg.xcache.protocol.GetCacheStatRequest;
import org.wg.xcache.protocol.GetCacheStatResponse;
import org.wg.xio.context.Context;
import org.wg.xio.ex.command.CommandRequest;

/**
 * 获取缓存统计信息
 * @author enychen Nov 12, 2009
 */
public class GetCacheStat extends XcacheCommand {

    /*
     * (non-Javadoc)
     * @see org.wg.xcache.server.command.XcacheCommand#execute(java.lang.Object,
     *      org.wg.xio.ex.command.CommandRequest, org.wg.xio.context.Context)
     */
    @Override
    protected void execute(Object request, CommandRequest commandRequest, Context context) {
        GetCacheStatRequest getCacheStatRequest = (GetCacheStatRequest) request;
        Cache cache = this.xcacheManager.getCache(getCacheStatRequest.getCacheName());

        CacheStat cacheStat = cache.getCacheStat();
        GetCacheStatResponse getCacheStatResponse = new GetCacheStatResponse();
        getCacheStatResponse.setCacheStat(cacheStat);

        this.writeResponse(getCacheStatResponse, commandRequest, context);
    }

}
