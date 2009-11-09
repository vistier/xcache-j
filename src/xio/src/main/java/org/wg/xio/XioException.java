package org.wg.xio;

/**
 * Xio异常
 * @author enychen Nov 10, 2009
 */
public class XioException extends RuntimeException {

    /** serialVersionUID = -4271129473794030191L */
    private static final long serialVersionUID = -4271129473794030191L;

    /**
     * 创建Xio异常
     */
    public XioException() {
        super();
    }

    /**
     * 创建Xio异常
     * @param message 异常信息
     */
    public XioException(String message) {
        super(message);
    }

    /**
     * 创建Xio异常
     * @param cause 原异常
     */
    public XioException(Throwable cause) {
        super(cause);
    }

    /**
     * 创建Xio异常
     * @param message 异常信息
     * @param cause 原异常
     */
    public XioException(String message, Throwable cause) {
        super(message, cause);
    }

}
