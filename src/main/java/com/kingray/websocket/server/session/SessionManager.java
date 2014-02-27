/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.Session;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 下午4:50:22
 */
public class SessionManager {
	//	private static Map<String, Session> sessionMap = new HashMap<String, Session>();
	private static final Object LOCK = new Object();

	private static final Collection<SessionWrapper> clients = new LinkedList<SessionWrapper>();

	public static boolean sessionCreated(String httpSessionId, Session websocketSession) {
		String userName = getUserBySessionId(httpSessionId);
		if (userName == null) {
			return false;
		}
		//		sessionMap.put(httpSessionId, websocketSession);
		SessionWrapper sessionWrapper = new SessionWrapper(websocketSession, userName,
				httpSessionId);
		clients.add(sessionWrapper);
		return true;
	}

	public static void sessionClosed(Session websocketSession) {
		if (websocketSession != null) {
			synchronized (LOCK) {
				for (SessionWrapper sessionWrapper : clients) {
					Session session = sessionWrapper.getSession();
					if (session.equals(websocketSession)) {
						clients.remove(session);
						break;
					}
				}
			}
			//			synchronized (LOCK) {
			//				List<String> keys = new LinkedList<String>();
			//				Set<Entry<String, Session>> entries = sessionMap.entrySet();
			//				for (Entry<String, Session> entry : entries) {
			//					String key = entry.getKey();
			//					Session value = entry.getValue();
			//					if (value.equals(websocketSession)) {
			//						keys.add(key);
			//					}
			//				}
			//				for (String key : keys) {
			//					sessionMap.remove(key);
			//				}
			//			}
		}
	}

	public static SessionWrapper getSessionWrapper(Session session) {
		for (SessionWrapper sessionWrapper : clients) {
			Session sessionVar = sessionWrapper.getSession();
			if (session.equals(sessionVar)) {
				return sessionWrapper;
			}
		}
		return null;
	}

	/**
	 * 检查指定的httpSessionId是否登录 <br>
	 * 2014年2月20日 下午5:09:54
	 * 
	 * @param httpSessionId
	 *            登录时的
	 * @return {@code String} userName
	 */
	private static String getUserBySessionId(String httpSessionId) {
		return null;//TODO
	}

	/**
	 * List<Session>
	 * 
	 * @return the clients
	 */
	public static Collection<SessionWrapper> getSessions() {
		return clients;
	}

	//	/**
	//	 * Map<String,Session>
	//	 * 
	//	 * @return the sessionMap
	//	 */
	//	public static Map<String, Session> getSessionMap() {
	//		return sessionMap;
	//	}

}
