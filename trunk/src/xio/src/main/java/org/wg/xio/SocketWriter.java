package org.wg.xio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wg.xio.context.Context;

/**
 * socket回写器
 * @author enychen Oct 18, 2009
 */
public class SocketWriter implements Runnable {

    /** log */
    private static final Log log = LogFactory.getLog(SocketWriter.class);

    /** 上下文 */
    private Context          context;

    /**
     * 创建socket回写器
     * @param context 上下文
     */
    public SocketWriter(Context context) {
        this.context = context;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {

    }

}
