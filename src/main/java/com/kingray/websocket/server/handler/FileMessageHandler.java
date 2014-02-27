/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午3:52:16
 */
public class FileMessageHandler extends AbstractHandler<InputStream>{

//	/**
//	 * <br>2013-11-14 下午3:54:35
//	 * @see javax.websocket.MessageHandler.Whole#onMessage(java.lang.Object)
//	 */
//	@Override
//	public void onMessage(InputStream message) {
//		EntityHelper.print(message);
//	}

	/**
	 * @param session
	 */
	public FileMessageHandler(Session session) {
		super(session);
	}

	/**
	 * <br>2013-11-14 下午4:05:45
	 * @see javax.websocket.MessageHandler.Partial#onMessage(java.lang.Object, boolean)
	 */
//	@Override
	public void onMessage(InputStream is) {
		EntityHelper.print(is);
		File file;
		OutputStream outputStream = null;
		try {
			file = File.createTempFile("upload", "");
			EntityHelper.print(file);
			
			outputStream = new FileOutputStream(file);
			
			byte[] buffer = new byte[64];
			for (int length = -1; (length = is.read(buffer)) >= 0; ) {
				EntityHelper.print(length);
				outputStream.write(buffer);
			}
			EntityHelper.print(file);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <br>2013-11-14 下午6:54:11
	 * @see javax.websocket.MessageHandler.Partial#onMessage(java.lang.Object, boolean)
	 */
//	@Override
	public void onMessage(InputStream is, boolean last) {
		EntityHelper.print(Thread.currentThread());
		EntityHelper.print(is);
		File file;
		OutputStream outputStream = null;
		try {
			file = File.createTempFile("upload", "");
			
			outputStream = new FileOutputStream(file);
			
			byte[] buffer = new byte[64];
			for (int length = -1; (length = is.read(buffer)) >= 0; ) {
				System.out.println("length ========== " + length);
				outputStream.write(buffer);
			}
			EntityHelper.print(file);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
