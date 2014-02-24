/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.kingray.websocket.server.config.Configurator;
import com.kingray.websocket.server.decode.WebSocketBinaryStreamDecoder;
import com.kingray.websocket.server.decode.WebSocketByteDecoder;
import com.kingray.websocket.server.encode.WebSocketEncoder;
import com.kingray.websocket.server.handler.FileMessageHandler;
import com.kingray.websocket.server.handler.StringMessageHandler;
import com.kingray.websocket.server.session.SessionManager;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 上午11:31:44
 */
@ServerEndpoint(value = "/websocket/{httpSessionId}", configurator = Configurator.class, encoders = { WebSocketEncoder.class }, decoders = {
		WebSocketByteDecoder.class, WebSocketBinaryStreamDecoder.class })
public class MyServerEndpoint {

	@OnOpen
	public void onOpen(Session session, EndpointConfig config,
			@PathParam("httpSessionId") String httpSessionId) {
		EntityHelper.print(" ------------ Session open ------------ ");
		EntityHelper.print(httpSessionId);
		String sessionId = session.getId();
		EntityHelper.print("sessionId = " + sessionId);

		boolean isSessionLogon = SessionManager.sessionCreated(httpSessionId, session);
		if (isSessionLogon) {// 如果客户端传入的sessionId已经登录，则存入
			session.addMessageHandler(new StringMessageHandler(session));
			//		session.addMessageHandler(new BytesMessageHandler());
			session.addMessageHandler(new FileMessageHandler(session));
		} else {

		}
		EntityHelper.print(session);
	}

	//	public static List<Session> getClients() {
	//		return clients;
	//	}

	//	@OnMessage
	//	public void onMessage(Session session, String message, @PathParam("client-id") String clientId) {
	//		EntityHelper.print(message);
	//		EntityHelper.print(clientId);
	//		for (Session client : clients) {
	//			try {
	//				client.getBasicRemote().sendText(message);
	//			} catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	}

	//
	//	@OnMessage
	//	public void onMessage(InputStream inputStream) {
	//		EntityHelper.print(inputStream);
	//	}

	@OnClose
	public void onClose(Session session) {
		System.out.println(" ------------ Session close ------------ ");
		SessionManager.sessionClosed(session);
		//		clients.remove(peer);
	}
}
