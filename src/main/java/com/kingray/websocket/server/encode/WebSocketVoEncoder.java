/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.encode;

import java.io.IOException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingray.websocket.vo.WebSocketVo;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午5:06:00
 */
public class WebSocketVoEncoder implements Encoder.Text<WebSocketVo>{
	
	public WebSocketVoEncoder(){
		EntityHelper.print(" ------------- WebSocketWebSocketVoEncoder init ------------- ");
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
	 * <br>2014年2月26日 下午7:58:04
	 * @see javax.websocket.Encoder.Text#encode(java.lang.Object)
	 */
	@Override
	public String encode(WebSocketVo object) throws EncodeException {
		ObjectMapper objectMapper = new ObjectMapper();
		String value = null;
		try {
			value = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new EncodeException(object, "无法解析该对象：" + object);
		}
		return value;
	}


}
