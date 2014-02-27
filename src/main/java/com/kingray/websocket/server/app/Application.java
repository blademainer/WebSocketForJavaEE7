/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.app;

import com.kingray.websocket.vo.WebSocketVo;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午2:35:22
 */
public interface Application {
	/**
	 * 当程序执行到该应用时调用execute接口方法 <br>
	 * 2014年2月24日 下午3:44:26
	 * 
	 * @param webSocketVo 上下文对象
	 * @return 返回的对象将传递给客户端
	 */
	public WebSocketVo execute(WebSocketVo webSocketVo);
	
}
