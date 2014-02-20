/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.handler;

import java.io.IOException;
import java.util.List;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

import com.kingray.websocket.server.MyServerEndpoint;
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
	@Override
	public void onMessage(String message) {
		EntityHelper.print(message);
		List<Session> clients = MyServerEndpoint.getClients();
		for (Session session : clients) {
			try {
				session.getBasicRemote().sendText(message);
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
