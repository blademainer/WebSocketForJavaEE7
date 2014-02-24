/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.event;

import com.kingray.websocket.server.session.SessionWrapper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月21日 上午10:31:11
 */
public interface SessionListener {
	/**
	 * 会话生成事件，一般为用户连接上websocket
	 * <br>2014年2月21日 上午10:32:07
	 * @param sessionWrapper
	 */
	public void sessionCreated(SessionWrapper sessionWrapper);
	
	/**
	 * 会话关闭事件，用户断开连接
	 * <br>2014年2月21日 上午10:32:55
	 * @param sessionWrapper
	 */
	public void sessionClosed(SessionWrapper sessionWrapper);
}
