/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.decode;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午5:06:00
 */
public class WebSocketStringDecoder implements Decoder.Text<String>{

	public WebSocketStringDecoder(){
		EntityHelper.print(" ------------- WebSocketStringDecoder init ------------- ");
	}
	
	/**
	 * <br>2013-11-14 下午5:06:16
	 * @see javax.websocket.Decoder#init(javax.websocket.EndpointConfig)
	 */
	@Override
	public void init(EndpointConfig config) {
		
	}

	/**
	 * <br>2013-11-14 下午5:06:16
	 * @see javax.websocket.Decoder#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	/**
	 * <br>2013-11-14 下午5:06:16
	 * @see javax.websocket.Decoder.Text#decode(java.lang.String)
	 */
	@Override
	public String decode(String s) throws DecodeException {
		EntityHelper.print(s);
		return null;
	}

	/**
	 * <br>2013-11-14 下午5:06:16
	 * @see javax.websocket.Decoder.Text#willDecode(java.lang.String)
	 */
	@Override
	public boolean willDecode(String s) {
		EntityHelper.print(s);
		return true;
	}

}
