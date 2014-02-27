/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.vo.jackson.deserializer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingray.websocket.vo.WebSocketVo;
import com.xiongyingqi.util.TimerHelper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月26日 上午10:16:25
 */
public class WebSocketVoDeserializer extends JsonDeserializer<WebSocketVo> {//

	/**
	 * <br>2014年2月26日 上午10:17:03
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public WebSocketVo deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		long id = 0L;
		String appId = null;
		String content = null;
		
		JsonNode idNode = node.get("id");
		JsonNode appIdNode = node.get("appId");
		JsonNode contentNode = node.get("content");
		if(idNode != null){
			id = idNode.asLong();
		}
		if(appIdNode != null){
			appId = appIdNode.asText();
		}
		if(contentNode != null){
			content = contentNode.toString();
		}
		
//		System.out.println("content ========= " + content);
		return new WebSocketVo(id, appId, content, null);
	}
	
	public static void main(String[] args) {
//		String message = "{\"id\":\"123\", \"appId\":\"login\", \"content\":\"{\"asf\":\"asd\"}\"}";
		String message = "{\"id\":\"123\", \"appId\":\"login\", \"content\":\"{'asf':'asd'}\"}";
		Pattern pattern = Pattern.compile("\\,[\\s]*[\\\"\\']content[\\\"\\'][\\s]*:[\\s]*[\\\"\\']\\{.*\\}[\\\"\\']");
		
		TimerHelper.getTime();
		for (int i = 0; i < 1000000; i++) {
			Matcher matcher = pattern.matcher(message);
			if (matcher.find()) {
				String content = matcher.group();
//				System.out.println(content);
				String rs = matcher.replaceFirst("");
//				System.out.println("rs = " + rs);
			}
		}
		System.out.println(TimerHelper.getTime());
		
		System.out.println(message);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(MapperFeature.USE_STATIC_TYPING, true);
		mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
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
