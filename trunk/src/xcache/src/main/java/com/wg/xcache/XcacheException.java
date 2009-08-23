package com.wg.xcache;

/**
 * Xcache异常
 * @author enychen Jul 20, 2009
 */
public class XcacheException extends RuntimeException {

    /** serialVersionUID = -75052388406675844L */
    private static final long serialVersionUID = -75052388406675844L;

    /**
     * 创建Xcache异常
     */
    public XcacheException() {
        super();
    }

    /**
     * 创建Xcache异常
     * @param message 异常信息
     */
    public XcacheException(String message) {
        super(message);
    }

    /**
     * 创建Xcache异常
     * @param cause 原异常
     */
    public XcacheException(Throwable cause) {
        super(cause);
    }

    /**
     * 创建Xcache异常
     * @param message 异常信息
     * @param cause 原异常
     */
    public XcacheException(String message, Throwable cause) {
        super(message, cause);
    }

}
