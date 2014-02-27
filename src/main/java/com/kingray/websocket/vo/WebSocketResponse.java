/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.vo;

import com.kingray.websocket.server.session.SessionWrapper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午3:39:51
 */
public class WebSocketResponse extends WebSocketVo{

	/**
	 * 2014年2月24日 下午3:40:21
	 * long WebSocketResponse.java
	 */
	private static final long serialVersionUID = 7352420980021371788L;

	/**
	 * @param id
	 * @param appId
	 * @param content
	 * @param sessionWrapper
	 */
	public WebSocketResponse(long id, String appId, Object content, SessionWrapper sessionWrapper) {
		super(id, appId, content, sessionWrapper);
	}

}
