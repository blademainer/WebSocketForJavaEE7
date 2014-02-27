/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.client;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 下午4:13:12
 */
public class MyContainerProvider extends ContainerProvider {

	/**
	 * <br>
	 * 2014年2月20日 下午4:14:13
	 * 
	 * @see javax.websocket.ContainerProvider#getContainer()
	 */
	@Override
	protected WebSocketContainer getContainer() {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		return container;
	}

	public static void main(String[] args) {
		WebSocketContainer c = ContainerProvider.getWebSocketContainer();
	}

}
