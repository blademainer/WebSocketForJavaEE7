/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.session;

import java.util.LinkedList;
import java.util.List;

import javax.websocket.Session;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 下午4:50:22
 */
public class SessionManager {
	private static final List<Session> clients = new LinkedList<Session>();
	
	public static boolean sessionCreated(String httpSessionId, Session websocketSession){
		String userName = getUserBySessionId(httpSessionId);
		if(userName == null){
			return false;
		}
		clients.add(websocketSession);
		return true;
	}
	
	public static void sessionClosed(Session websocketSession){
		if(websocketSession != null && clients.contains(websocketSession)){
			clients.remove(websocketSession);
		}
	}
	
	/**
	 * 检查指定的httpSessionId是否登录
	 * <br>2014年2月20日 下午5:09:54
	 * @param httpSessionId 登录时的
	 * @return {@code String} userName
	 */
	private static String getUserBySessionId(String httpSessionId) {
		return null;//TODO
	}

	/**
	 * List<Session>
	 * @return the clients
	 */
	public static List<Session> getClients() {
		return clients;
	}
}
