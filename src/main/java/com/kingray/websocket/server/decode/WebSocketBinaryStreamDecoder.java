/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.decode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.kingray.vo.UploadFile;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午3:42:40
 */
public class WebSocketBinaryStreamDecoder implements Decoder.BinaryStream<UploadFile>{

	public WebSocketBinaryStreamDecoder(){
		EntityHelper.print(" ------------- WebSocketBinaryStreamDecoder init ------------- ");
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
	 * <br>2013-11-14 下午3:42:54
	 * @see javax.websocket.Decoder.BinaryStream#decode(java.io.InputStream)
	 */
	@Override
	public UploadFile decode(InputStream is) throws DecodeException, IOException {
		File file = File.createTempFile("upload", "");
		EntityHelper.print(file);
		
		OutputStream outputStream = new FileOutputStream(file);
		
		byte[] buffer = new byte[64];
		for (int length = -1; (length = is.read(buffer)) >= 0; ) {
			outputStream.write(buffer);
		}
		
		EntityHelper.print(file);
		
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFile(file);
		return uploadFile;
	}

}
