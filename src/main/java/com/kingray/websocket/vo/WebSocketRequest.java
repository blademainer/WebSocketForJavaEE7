/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.vo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import com.kingray.websocket.server.session.SessionWrapper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午3:24:15
 */
public class WebSocketRequest extends WebSocketVo{
	
	/**
	 * 2014年2月24日 下午3:24:26
	 * long WebSocketRequest.java
	 */
	private static final long serialVersionUID = 2378276556283559815L;
	
	private Map<String, String[]> parameterMap;
	
	/**
	 * @param id
	 * @param appId
	 * @param content
	 * @param sessionWrapper
	 */
	public WebSocketRequest(long id, String appId, Object content, SessionWrapper sessionWrapper, String queryString) {
		super(id, appId, content, sessionWrapper);
		buildParameterMap(queryString);
	}
	
	public WebSocketResponse buildResponse(){
		return new WebSocketResponse(id, appId, content, sessionWrapper);
	}
	
	@SuppressWarnings("unchecked")
	private void buildParameterMap(String queryString){
		Map<String, Set<String>> parameterMap = new HashMap<String, Set<String>>();
		StringTokenizer stringTokenizer = new StringTokenizer(queryString, "&");
		while (stringTokenizer.hasMoreElements()) {
			String parameter = (String) stringTokenizer.nextElement();
			System.out.println(parameter);

			StringTokenizer parameterTokenizer = new StringTokenizer(parameter, "=");

			String key = null;
			String value = null;
			if (parameterTokenizer.hasMoreElements()) {
				key = (String) parameterTokenizer.nextElement();
			}
			if (parameterTokenizer.hasMoreElements()) {
				value = (String) parameterTokenizer.nextElement();
			}
			System.out.println("key ========== " + key);
			System.out.println("value ========== " + value);
			putKeyAndValueToMap(parameterMap, key, value);
		}
		this.parameterMap = parseSetToArray(parameterMap);
	}
	
	private Map parseSetToArray(Map<String, Set<String>> map){
		if(map == null){
			return null;
		}
		Map<String, String[]> rsMap = new HashMap<String, String[]>();
		Set<Entry<String, Set<String>>> entries = map.entrySet();
		for (Entry<String, Set<String>> entry : entries) {
			String key = entry.getKey();
			Set<String> value = entry.getValue();
			if(value == null){
				rsMap.put(key, null);
			} else {
				String[] strings = value.toArray(new String[]{});
				rsMap.put(key, strings);
			}
		}
		return rsMap;
	}

	private void putKeyAndValueToMap(Map<String, Set<String>> map, String key, String value) {
		Set<String> values = map.get(key);
		if (values == null) {
			values = new HashSet<String>();
		}
		values.add(value);
		map.put(key, values);
	}

	/**
	 * Map<String,String[]>
	 * @return the parameterMap
	 */
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}
	
}
