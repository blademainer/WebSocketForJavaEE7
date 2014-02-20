/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.encode;

import java.nio.ByteBuffer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.kingray.vo.UploadFile;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午2:41:08
 */
public class WebSocketEncoder implements Encoder.Binary<UploadFile>{

	/**
	 * <br>2013-11-14 下午2:41:18
	 * @see javax.websocket.Encoder#init(javax.websocket.EndpointConfig)
	 */
	@Override
	public void init(EndpointConfig endpointConfig) {
		
	}

	/**
	 * <br>2013-11-14 下午2:41:18
	 * @see javax.websocket.Encoder#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	/**
	 * <br>2013-11-14 下午5:00:20
	 * @see javax.websocket.Encoder.Binary#encode(java.lang.Object)
	 */
	@Override
	public ByteBuffer encode(UploadFile object) throws EncodeException {
		EntityHelper.print(object);
		return null;
	}

}
