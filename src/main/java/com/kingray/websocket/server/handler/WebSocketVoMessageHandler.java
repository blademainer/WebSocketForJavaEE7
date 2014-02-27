/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.handler;

import javax.websocket.Session;

import com.kingray.websocket.vo.WebSocketVo;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月26日 下午6:32:26
 */
public class WebSocketVoMessageHandler extends AbstractHandler<WebSocketVo> {

	/**
	 * @param session
	 */
	public WebSocketVoMessageHandler(Session session) {
		super(session);
	}

	/**
	 * <br>2014年2月26日 下午6:38:50
	 * @see javax.websocket.MessageHandler.Whole#onMessage(java.lang.Object)
	 */
//	@Override
	public void onMessage(WebSocketVo message) {
		EntityHelper.print(message);
	}

	/**
	 * <br>2014年2月26日 下午7:50:33
	 * @see javax.websocket.MessageHandler.Partial#onMessage(java.lang.Object, boolean)
	 */
//	@Override
	public void onMessage(WebSocketVo partialMessage, boolean last) {
		EntityHelper.print(partialMessage);
	}

}
