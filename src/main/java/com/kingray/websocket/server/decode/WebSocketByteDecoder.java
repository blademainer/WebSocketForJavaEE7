/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.decode;

import java.nio.ByteBuffer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.kingray.vo.UploadFile;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午2:41:40
 */
public class WebSocketByteDecoder implements Decoder.Binary<UploadFile>{

	public WebSocketByteDecoder(){
		EntityHelper.print(" ------------- WebSocketByteDecoder init ------------- ");
	}
	/**
	 * <br>2013-11-14 下午3:42:54
	 * @see javax.websocket.Decoder#init(javax.websocket.EndpointConfig)
	 */
	@Override
	public void init(EndpointConfig config) {
		EntityHelper.print("init");
	}

	/**
	 * <br>2013-11-14 下午3:42:54
	 * @see javax.websocket.Decoder#destroy()
	 */
	@Override
	public void destroy() {
		EntityHelper.print("destroy");
	}


	/**
	 * <br>2013-11-14 下午3:39:51
	 * @see javax.websocket.Decoder.Binary#decode(java.nio.ByteBuffer)
	 */
	@Override
	public UploadFile decode(ByteBuffer bytes) throws DecodeException {
		EntityHelper.print("decode");
		EntityHelper.print(bytes.array().length);
		return null;
	}

	/**
	 * <br>2013-11-14 下午3:39:51
	 * @see javax.websocket.Decoder.Binary#willDecode(java.nio.ByteBuffer)
	 */
	@Override
	public boolean willDecode(ByteBuffer bytes) {
		EntityHelper.print("willDecode");
		return true;
	}

}
