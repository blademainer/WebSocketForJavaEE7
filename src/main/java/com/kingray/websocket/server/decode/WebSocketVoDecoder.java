/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.decode;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingray.websocket.server.route.WebSocketMessageRouter;
import com.kingray.websocket.vo.WebSocketVo;
import com.sun.mail.util.DecodingException;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午5:06:00
 */
public class WebSocketVoDecoder implements Decoder.Text<WebSocketVo> {
	private ThreadLocal<ObjectMapper> _mapper = new ThreadLocal<ObjectMapper>() {
		@Override
		protected ObjectMapper initialValue() {
			return new ObjectMapper();
		}
	};

	/**
	 * <br>
	 * 2013-11-14 下午5:06:16
	 * 
	 * @see javax.websocket.Decoder#init(javax.websocket.EndpointConfig)
	 */
	@Override
	public void init(EndpointConfig config) {
	}

	/**
	 * <br>
	 * 2014年2月27日 上午11:14:59
	 * 
	 * @see javax.websocket.Decoder#destroy()
	 */
	@Override
	public void destroy() {

	}

	/**
	 * <br>
	 * 2014年2月27日 上午11:14:59
	 * 
	 * @see javax.websocket.Decoder.Text#decode(java.lang.String)
	 */
	@Override
	public WebSocketVo decode(final String string) throws DecodeException {
//		Thread thread = new Thread(){
//			/**
//			 * <br>2014年2月27日 下午12:39:20
//			 * @see java.lang.Thread#run()
//			 */
//			@Override
//			public void run() {
//				ObjectMapper mapper = new ObjectMapper();
//				try {
//					WebSocketVo webSocketVo = mapper.readValue(string, WebSocketVo.class);
//					EntityHelper.print(webSocketVo);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		thread.start();
		
//		if (willDecode(string)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				WebSocketVo webSocketVo = mapper.readValue(string, WebSocketVo.class);
//				WebSocketVo webSocketVo = new WebSocketVo();
				EntityHelper.print(webSocketVo);
				return webSocketVo;
			} catch (JsonParseException e) {
				e.printStackTrace();
				throw new DecodeException(string, "[Message] Can't decode.");
			} catch (JsonMappingException e) {
				e.printStackTrace();
				throw new DecodeException(string, "[Message] Can't decode.");
			} catch (IOException e) {
				e.printStackTrace();
				throw new DecodeException(string, "[Message] Can't decode.");
			}
//		} else {
//			throw new DecodeException(string, "[Message] Can't decode.");
//		}

		//		EntityHelper.print(s);
		//		ObjectMapper mapper = new ObjectMapper();
		//		try {
		//			WebSocketVo webSocketVo = mapper.readValue(s, WebSocketVo.class);
		//			EntityHelper.print(webSocketVo);
		//			return mapper.readValue(s, WebSocketVo.class);
		//		} catch (JsonParseException e) { 
		//			e.printStackTrace();
		//			throw new DecodeException(s, e.getMessage());
		//		} catch (JsonMappingException e) {
		//			e.printStackTrace();
		//			throw new DecodeException(s, e.getMessage());
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//			throw new DecodeException(s, e.getMessage());
		//		}
	}

	/**
	 * <br>
	 * 2014年2月27日 上午11:14:59
	 * 
	 * @see javax.websocket.Decoder.Text#willDecode(java.lang.String)
	 */
	@Override
	public boolean willDecode(String string) {
//		boolean decodes = false;
//		/* Convert the message into a map */
//		messageMap = new HashMap<>();
//		JsonParser parser = Json.createParser(new StringReader(string));
//		while (parser.hasNext()) {
//			if (parser.next() == JsonParser.Event.KEY_NAME) {
//				String key = parser.getString();
//				parser.next();
//				String value = parser.getString();
//				messageMap.put(key, value);
//			}
//		}
//		/* Check the kind of message and if all fields are included */
//		Set keys = messageMap.keySet();
//		if (keys.contains("appid")) {
//			decodes = true;
//		}
		return true;
	}

}
