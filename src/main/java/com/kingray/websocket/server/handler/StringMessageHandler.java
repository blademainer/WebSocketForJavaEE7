/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.websocket.Session;

import com.kingray.websocket.server.route.WebSocketMessageRouter;
import com.kingray.websocket.server.session.SessionManager;
import com.kingray.websocket.server.session.SessionWrapper;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午3:51:09
 */
public class StringMessageHandler extends AbstractHandler<String> {//implements MessageHandler.Whole<String>

	/**
	 * @param session
	 */
	public StringMessageHandler(Session session) {
		super(session);
	}

	/**
	 * <br>2013-11-14 下午3:51:49
	 * @see javax.websocket.MessageHandler.Whole#onMessage(java.lang.Object)
	 */
//	@Override
	public void onMessage(String message) {
		WebSocketMessageRouter.route(message, session);
		EntityHelper.print(message);
		EntityHelper.print(Thread.currentThread());
		Collection<SessionWrapper> clients = SessionManager.getSessions();
		for (Session session : clients) {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <br>2014年2月26日 下午7:49:59
	 * @see javax.websocket.MessageHandler.Partial#onMessage(java.lang.Object, boolean)
	 */
//	@Override
	public void onMessage(String partialMessage, boolean last) {
		WebSocketMessageRouter.route(partialMessage, session);
		EntityHelper.print(partialMessage);
		EntityHelper.print(Thread.currentThread());
		Collection<SessionWrapper> clients = SessionManager.getSessions();
		for (Session session : clients) {
			try {
				session.getBasicRemote().sendText(partialMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	/**
//	 * <br>2013-11-14 下午4:05:33
//	 * @see javax.websocket.MessageHandler.Partial#onMessage(java.lang.Object, boolean)
//	 */
//	@Override
//	public void onMessage(String partialMessage, boolean last) {
//		EntityHelper.print(partialMessage);
//		EntityHelper.print(last);
//	}
	
}
