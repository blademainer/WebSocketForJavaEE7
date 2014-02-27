/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.app;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午3:10:35
 */
public class RegisterApplicationException extends Exception {
	/**
	 * 2014年2月24日 下午2:55:01
	 * long AbstractApplication.java
	 */
	private static final long serialVersionUID = -8903333329667891534L;
	
    public RegisterApplicationException() {
        super();
    }

    public RegisterApplicationException(String message) {
        super(message);
    }

    public RegisterApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterApplicationException(Throwable cause) {
        super(cause);
    }

    protected RegisterApplicationException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
