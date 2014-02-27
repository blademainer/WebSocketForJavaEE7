/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.route;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingray.websocket.server.app.AbstractApplication;
import com.kingray.websocket.server.app.NoSuchApplicationException;
import com.kingray.websocket.server.session.SessionManager;
import com.kingray.websocket.server.session.SessionWrapper;
import com.kingray.websocket.vo.WebSocketVo;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午3:18:55
 */
public class WebSocketMessageRouter {
	//	private static WebSocketMessageRouter singleton = new WebSocketMessageRouter();
	/**
	 * 查找字符串，结果类似如下：<br>
	 * , "content":"{"asf":"asd", "asf":"fff", ss:"{"aaa":"ffff",
	 * "ssss":"sss"}"}"
	 * 
	 * <pre>
	 * private static final Pattern PATTERN_FIND_CONTENT = Pattern
	 * 		.compile(&quot;\\,[\\s]*[\\\&quot;\\']content[\\\&quot;\\'][\\s]*:[\\s]*[\\\&quot;\\']\\{.*\\}[\\\&quot;\\']&quot;);
	 * </pre>
	 */
	/**
	 * 查找大括号中的内容
	 * 
	 * <pre>
	 * private static final Pattern PATTERN_FIND_BRACE = Pattern.compile(&quot;\\{.*\\}&quot;);
	 * </pre>
	 */
	private static long current_id = 0;
	public static final Object LOCK = new Object();

	//	private WebSocketMessageRouter() {
	//	}
	//
	//	public static WebSocketMessageRouter getInstance() {
	//		return singleton;
	//	}

	/**
	 * 对消息进行解析 <br>
	 * 2014年2月24日 下午4:57:36
	 * 
	 * @param message
	 * @param session
	 */
	public static void route(String message, Session session) {
		try {
			WebSocketVo webSocketVo = parseSocketVo(message);
			route(webSocketVo, session);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对消息进行解析 <br>
	 * 2014年2月24日 下午4:57:36
	 * 
	 * @param message
	 * @param session
	 */
	public static void route(WebSocketVo webSocketVo, Session session) {
		try {
			if(webSocketVo.getId() == 0){
				webSocketVo.setId(nextId());
			}
			SessionWrapper sessionWrapper = SessionManager.getSessionWrapper(session);
			webSocketVo.setSessionWrapper(sessionWrapper);
			WebSocketVo webSocketVoReturn = AbstractApplication.doExecute(webSocketVo);// 将消息传递到application
			try {
				session.getBasicRemote().sendObject(webSocketVoReturn);// 发送返回的消息
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EncodeException e) {
				e.printStackTrace();
			}
		} catch (NoSuchApplicationException e) {
			e.printStackTrace();
		}
	}

	public static WebSocketVo parseSocketVo(String message) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//		Matcher matcher = PATTERN_FIND_CONTENT.matcher(message);
		//		String content = null;
		//		if (matcher.find()) {
		//			content = matcher.group();
		//			
		//			Matcher matcherBrace = PATTERN_FIND_BRACE.matcher(content);
		//			if(matcherBrace.find()){
		//				content = matcherBrace.group();
		//			}
		//			message = matcher.replaceFirst("");
		//		}

		WebSocketVo webSocketVo = mapper.readValue(message, WebSocketVo.class);
		//		if (webSocketVo.getId() == 0) {
		//			webSocketVo.setId(nextId());
		//		}
		//		if (content != null) {
		//			webSocketVo.setContent(content);
		//		}
		return webSocketVo;
	}

	private static long nextId() {
		synchronized (LOCK) {
			if (++current_id == 0) {
				current_id++;
			}
		}
		return current_id;
	}

	public static void main(String[] args) {
		String message = "{\"id\":\"123\", \"appId\":\"login\", \"content\":{\"asf\":\"asd\"}}";
		System.out.println(message);
		ObjectMapper mapper = new ObjectMapper();
		//		mapper.enableDefaultTyping(DefaultTyping.NON_FINAL);
		try {
			WebSocketVo webSocketVo = mapper.readValue(message, WebSocketVo.class);
			System.out.println(webSocketVo);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
