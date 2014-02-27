/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.app.login;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kingray.websocket.server.annotation.WebSocketApplication;
import com.kingray.websocket.server.app.AbstractApplication;
import com.kingray.websocket.vo.WebSocketVo;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午4:24:18
 */
@WebSocketApplication("login")
public class LoginApplication extends AbstractApplication<LoginVo>{

	/**
	 * <br>2014年2月24日 下午4:24:53
	 * @see com.kingray.websocket.server.app.Application#execute(com.kingray.websocket.vo.WebSocketRequest)
	 */
	@Override
	public WebSocketVo execute(WebSocketVo webSocketVo) {
		System.out.println("id: " + webSocketVo.getId());
//		System.out.println(" ----------------------- LoginApplication do execute ----------------------- ");
		try {
			LoginVo loginVo = super.getModel();
			if(loginVo.getUserName() != null && loginVo.getPassword() != null){
				WebSocketVo webSocketVoResult = webSocketVo.createResultSocketVo();
				webSocketVoResult.setContent(LoginVoReturn.LOGIN_SUCCESS);
				return webSocketVoResult;
			}
			System.out.println(loginVo);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
