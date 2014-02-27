/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.app;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午3:10:35
 */
public class NoSuchApplicationException extends Exception {
	/**
	 * 2014年2月24日 下午2:59:50
	 * long AbstractApplication.java
	 */
	private static final long serialVersionUID = -8742805918820098086L;
    public NoSuchApplicationException() {
        super();
    }

    public NoSuchApplicationException(String message) {
        super(message);
    }

    public NoSuchApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchApplicationException(Throwable cause) {
        super(cause);
    }

    protected NoSuchApplicationException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
