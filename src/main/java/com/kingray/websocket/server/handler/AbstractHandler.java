/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.handler;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 上午9:57:32
 */
public abstract class AbstractHandler<T> implements MessageHandler.Whole<T> {
	Session session;
	
	public AbstractHandler(Session session){
		this.session = session;
	}
}
