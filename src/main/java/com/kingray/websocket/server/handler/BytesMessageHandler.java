/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.handler;

import javax.websocket.Session;

import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午4:03:24
 */
public class BytesMessageHandler extends AbstractHandler<byte[]>{

	/**
	 * @param session
	 */
	public BytesMessageHandler(Session session) {
		super(session);
	}

	/**
	 * <br>2013-11-14 下午4:03:39
	 * @see javax.websocket.MessageHandler.Partial#onMessage(java.lang.Object, boolean)
	 */
	@Override
	public void onMessage(byte[] partialMessage) {
		EntityHelper.print(partialMessage);
	}

}
