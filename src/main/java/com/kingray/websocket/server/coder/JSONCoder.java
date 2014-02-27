/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午5:06:00
 */
public abstract class JSONCoder<T> implements Encoder.Text<T>, Decoder.Text<T>
//,Decoder.TextStream<T>, Encoder.TextStream<T>, Encoder.BinaryStream<T>, Decoder.BinaryStream<T>  
{
	private Class<T> _type;

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
	@SuppressWarnings("unchecked")
	@Override
	public void init(EndpointConfig config) {
		EntityHelper.print(" ------------- JSONCoder init ------------- ");

		ParameterizedType $thisClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type $T = $thisClass.getActualTypeArguments()[0];
		if ($T instanceof Class) {
			_type = (Class<T>) $T;
		} else if ($T instanceof ParameterizedType) {
			_type = (Class<T>) ((ParameterizedType) $T).getRawType();
		}
	}

	/**
	 * <br>
	 * 2013-11-14 下午5:06:16
	 * 
	 * @see javax.websocket.Decoder#destroy()
	 */
	@Override
	public void destroy() {

	}

	/**
	 * <br>
	 * 2013-11-14 下午5:06:16
	 * 
	 * @see javax.websocket.Decoder.Text#willDecode(java.lang.String)
	 */
//	@Override
	public boolean willDecode(String string) {
//		EntityHelper.print(string);
//
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
//		if (keys.contains("appId")) {
//			decodes = true;
//		}
		return true;
	}

	/**
	 * <br>
	 * 2014年2月26日 下午6:21:15
	 * 
	 * @see javax.websocket.Decoder.Text#decode(java.lang.String)
	 */
//	@Override
	public T decode(String string) throws DecodeException {
		EntityHelper.print(string);

		if (willDecode(string)) {
			T t;
			try {
				t = _mapper.get().readValue(string, _type);
				EntityHelper.print(t);
				return t;
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
		} else {
			throw new DecodeException(string, "[Message] Can't decode.");
		}
	}

	/**
	 * <br>
	 * 2014年2月26日 下午10:17:14
	 * 
	 * @see javax.websocket.Encoder.Text#encode(java.lang.Object)
	 */
//	@Override
	public String encode(T object) throws EncodeException {
		EntityHelper.print(object);
		try {
			return _mapper.get().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <br>
	 * 2014年2月26日 下午10:17:29
	 * 
	 * @see javax.websocket.Decoder.BinaryStream#decode(java.io.InputStream)
	 */
	public T decode(InputStream is) throws DecodeException, IOException {
		EntityHelper.print("decode");
		return null;
	}

	/**
	 * <br>
	 * 2014年2月26日 下午10:17:57
	 * 
	 * @see javax.websocket.Encoder.BinaryStream#encode(java.lang.Object,
	 *      java.io.OutputStream)
	 */
	public void encode(T object, OutputStream os) throws EncodeException, IOException {
		EntityHelper.print(object);
	}

	/**
	 * <br>
	 * 2014年2月26日 下午9:14:26
	 * 
	 * @see javax.websocket.Decoder.TextStream#decode(java.io.Reader)
	 */
	public T decode(Reader reader) throws DecodeException, IOException {
		EntityHelper.print(" ------------- JSONCoder decode ------------- "
				+ _mapper.get().readValue(reader, _type));
		return _mapper.get().readValue(reader, _type);
	}

	/**
	 * <br>
	 * 2014年2月26日 下午9:17:32
	 * 
	 * @see javax.websocket.Encoder.TextStream#encode(java.lang.Object,
	 *      java.io.Writer)
	 */
	public void encode(T object, Writer writer) throws EncodeException, IOException {
		EntityHelper.print(" ------------- JSONCoder encode ------------- ");
		_mapper.get().writeValue(writer, object);
	}

}
