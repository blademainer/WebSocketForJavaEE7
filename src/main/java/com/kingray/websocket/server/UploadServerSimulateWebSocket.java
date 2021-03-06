/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * 模拟服务端
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 下午4:42:03
 */
public class UploadServerSimulateWebSocket {
	private int port = 8081;
	private ServerSocket serverSocket;

	public UploadServerSimulateWebSocket() throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("服务器启动");
	}

	private void service() {
		Socket socket = null;
		while (true) {
			try {
				socket = serverSocket.accept();
				Thread workThread = new Thread(new Handler(socket));
				workThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Handler implements Runnable {
		private Socket socket;
		private boolean hasHandshake = false;
		Charset charset = Charset.forName("UTF-8");
		private File file = null;
		private FileOutputStream fileOut = null;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		private PrintWriter getWriter(Socket socket) throws IOException {
			OutputStream socketOut = socket.getOutputStream();
			return new PrintWriter(socketOut, true);
		}

		public String echo(String msg) {
			return "echo:" + msg;
		}

		public void run() {

			try {
				System.out.println("New connection accepted" + socket.getInetAddress() + ":"
						+ socket.getPort());
				InputStream in = socket.getInputStream();

				PrintWriter pw = getWriter(socket);
				//读入缓存
				byte[] buf = new byte[1024];
				//读到字节
				int len = in.read(buf, 0, 1024);
				//读到字节数组
				byte[] res = new byte[len];
				System.arraycopy(buf, 0, res, 0, len);
				String key = new String(res);
				if (!hasHandshake && key.indexOf("Key") > 0) {
					key = key.substring(0, key.indexOf("==") + 2);
					key = key.substring(key.indexOf("Key") + 4, key.length()).trim();
					key += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
					MessageDigest md = MessageDigest.getInstance("SHA-1");
					md.update(key.getBytes("utf-8"), 0, key.length());
					byte[] sha1Hash = md.digest();
					sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
					key = encoder.encode(sha1Hash);
					pw.println("HTTP/1.1 101 Switching Protocols");
					pw.println("Upgrade: websocket");
					pw.println("Connection: Upgrade");
					pw.println("Sec-WebSocket-Accept: " + key);
					pw.println();
					pw.flush();
					hasHandshake = true;

					//接收数据
					byte[] first = new byte[1];
					int read = in.read(first, 0, 1);
					while (read > 0) {
						int b = first[0] & 0xFF;
						//boolean fin = (b & 0x80) > 0;
						// int rsv = (b & 0x70) >>> 4;
						byte opCode = (byte) (b & 0x0F);

						if (opCode == 8) {
							socket.getOutputStream().close();
							file = null;
							fileOut.flush();
							fileOut.close();
							fileOut = null;
							break;
						}
						b = in.read();
						int payloadLength = b & 0x7F;
						if (payloadLength == 126) {
							byte[] extended = new byte[2];
							in.read(extended, 0, 2);
							int shift = 0;
							payloadLength = 0;
							for (int i = extended.length - 1; i >= 0; i--) {
								payloadLength = payloadLength + ((extended[i] & 0xFF) << shift);
								shift += 8;
							}

						} else if (payloadLength == 127) {
							byte[] extended = new byte[8];
							in.read(extended, 0, 8);
							int shift = 0;
							payloadLength = 0;
							for (int i = extended.length - 1; i >= 0; i--) {
								payloadLength = payloadLength + ((extended[i] & 0xFF) << shift);
								shift += 8;
							}
						}

						//掩码
						byte[] mask = new byte[4];
						in.read(mask, 0, 4);
						int readThisFragment = 1;
						ByteBuffer byteBuf = ByteBuffer.allocate(payloadLength);
						while (payloadLength > 0) {
							int masked = in.read();
							masked = masked ^ (mask[(int) ((readThisFragment - 1) % 4)] & 0xFF);
							byteBuf.put((byte) masked);
							payloadLength--;
							readThisFragment++;
						}
						byteBuf.flip();
						if (opCode == 1) {
							getChar(byteBuf.array());
						} else
							outFile(byteBuf.array());
						in.read(first, 0, 1);
					}

				}
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void responseClient(boolean finalFragment) throws IOException {
			ByteBuffer byteBuf = ByteBuffer.allocate(10);
			byteBuf.put("ok".getBytes("UTF-8"));
			OutputStream out = socket.getOutputStream();
			int first = 0x00;
			//是否是输出最后的WebSocket响应片段，默认
			if (finalFragment) {
				first = first + 0x80;
				first = first + 0x1;
			}
			out.write(first);

			if (byteBuf.limit() < 126) {
				out.write(byteBuf.limit());
			} else if (byteBuf.limit() < 65536) {
				out.write(126);
				out.write(byteBuf.limit() >>> 8);
				out.write(byteBuf.limit() & 0xFF);
			} else {
				// Will never be more than 2^31-1
				out.write(127);
				out.write(0);
				out.write(0);
				out.write(0);
				out.write(0);
				out.write(byteBuf.limit() >>> 24);
				out.write(byteBuf.limit() >>> 16);
				out.write(byteBuf.limit() >>> 8);
				out.write(byteBuf.limit() & 0xFF);

			}

			// Write the content
			out.write(byteBuf.array(), 0, byteBuf.limit());
			out.flush();
		}

		/**
		 * 方法说明:
		 * 
		 * @开发：linrb
		 * @创建时间：2012-8-21
		 * @param array
		 * @throws IOException
		 */
		private void getChar(byte[] array) throws IOException {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(array);
			InputStreamReader reader = new InputStreamReader(byteIn, charset.newDecoder());
			int b = 0;
			String res = "";
			try {
				while ((b = reader.read()) > 0) {
					res += (char) b;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			file = new File("D:/" + res);
		}

		/**
		 * 方法说明:
		 * 
		 * @开发：linrb
		 * @创建时间：2012-8-14
		 * @param array
		 * @throws IOException
		 */
		private void outFile(byte[] array) throws IOException {
			if (fileOut == null) {
				fileOut = new FileOutputStream(file, true);
			}
			fileOut.write(array);
			responseClient(true);
		}
	}

	public static void main(String[] args) throws IOException {
		new UploadServerSimulateWebSocket().service();
	}
}
