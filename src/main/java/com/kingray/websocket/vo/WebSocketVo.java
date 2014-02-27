/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kingray.websocket.server.coder.JSONCoder;
import com.kingray.websocket.server.session.SessionWrapper;
import com.kingray.websocket.vo.jackson.deserializer.WebSocketVoDeserializer;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 下午3:42:34
 */
@JsonDeserialize(using = WebSocketVoDeserializer.class)
public class WebSocketVo implements VO {
	
	public static class WebSocketVoCoderBean extends JSONCoder<WebSocketVo>{
		
	}
	
	/**
	 * 2014年2月20日 下午3:45:23 long WebSocketVo.java
	 */
	private static final long serialVersionUID = 2548674468359882645L;

	protected long id;
	protected String appId;
	protected Object content;
	@JsonIgnore
	protected SessionWrapper sessionWrapper;

	public WebSocketVo() {
	}

	public WebSocketVo(long id, String appId, Object content, SessionWrapper sessionWrapper) {
		this.id = id;
		this.appId = appId;
		this.content = content;
		this.sessionWrapper = sessionWrapper;
	}
	
	public WebSocketVo createResultSocketVo(){
		WebSocketVo webSocketVo = new WebSocketVo(id, appId, null, sessionWrapper);
		return webSocketVo;
	}

	/**
	 * int
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * int
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * String
	 * 
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * String
	 * 
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * String
	 * 
	 * @return the content
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * String
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(Object content) {
		this.content = content;
	}

	/**
	 * SessionWrapper
	 * 
	 * @return the sessionWrapper
	 */
	public SessionWrapper getSessionWrapper() {
		return sessionWrapper;
	}

	/**
	 * SessionWrapper
	 * 
	 * @param sessionWrapper
	 *            the sessionWrapper to set
	 */
	public void setSessionWrapper(SessionWrapper sessionWrapper) {
		this.sessionWrapper = sessionWrapper;
	}

	/**
	 * <br>
	 * 2014年2月24日 下午3:03:41
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return EntityHelper.reflectToString(this);
	}

	/**
	 * <br>
	 * 2014年2月24日 下午3:03:59
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return EntityHelper.hashCode(this);
	}

}
