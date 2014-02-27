/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.app.chat;

import com.kingray.websocket.server.annotation.WebSocketApplication;
import com.kingray.websocket.server.app.AbstractApplication;
import com.kingray.websocket.vo.VO;
import com.kingray.websocket.vo.WebSocketVo;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月25日 下午3:48:13
 */
@WebSocketApplication("chat")
public class KingrayToneChatApplication extends AbstractApplication<ChatVo>{

	/**
	 * <br>2014年2月25日 下午3:48:22
	 * @see com.kingray.websocket.server.app.Application#execute(com.kingray.websocket.vo.WebSocketVo)
	 */
	@Override
	public WebSocketVo execute(WebSocketVo webSocketVo) {
		System.out.println(" ----------------------- KingrayToneChatApplication do execute ----------------------- ");
		return null;
	}

	

}
