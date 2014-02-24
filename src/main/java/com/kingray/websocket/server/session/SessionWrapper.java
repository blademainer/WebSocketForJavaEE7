/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.session;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * websocket session包装类
 * 
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 下午5:22:22
 */
public class SessionWrapper implements Session {
	private Session session;
	/**
	 * 用户标识
	 */
	private String userName;
	private String httpSessionId;
	private Map<String, Object> attributeMap = new HashMap<String, Object>();
	private static final Object LOCK = new Object();

	public SessionWrapper(Session session, String userName, String httpSessionId) {
		this.session = session;
		this.userName = userName;
		this.httpSessionId = httpSessionId;
	}
	
	public void setAttribute(String attributeKey, Object attributeValue) {
		synchronized (LOCK) {
			attributeMap.put(attributeKey, attributeValue);
		}
	}

	public Object getAttribute(String attributeKey) {
		Object value = null;
		synchronized (LOCK) {
			value = attributeMap.get(attributeKey);
		}
		return value;
	}

	public Map<String, Object> getAttributeMap() {
		return attributeMap;
	}

	
	/**
	 * String
	 * @return the httpSessionId
	 */
	public String getHttpSessionId() {
		return httpSessionId;
	}

	/**
	 * Session
	 * 
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Object
	 * 
	 * @return the user
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getContainer()
	 */
	@Override
	public WebSocketContainer getContainer() {
		return session.getContainer();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#addMessageHandler(javax.websocket.MessageHandler)
	 */
	@Override
	public void addMessageHandler(MessageHandler handler) throws IllegalStateException {
		session.addMessageHandler(handler);
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getMessageHandlers()
	 */
	@Override
	public Set<MessageHandler> getMessageHandlers() {
		return session.getMessageHandlers();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#removeMessageHandler(javax.websocket.MessageHandler)
	 */
	@Override
	public void removeMessageHandler(MessageHandler handler) {
		session.removeMessageHandler(handler);
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getProtocolVersion()
	 */
	@Override
	public String getProtocolVersion() {
		return session.getProtocolVersion();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getNegotiatedSubprotocol()
	 */
	@Override
	public String getNegotiatedSubprotocol() {
		return session.getNegotiatedSubprotocol();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getNegotiatedExtensions()
	 */
	@Override
	public List<Extension> getNegotiatedExtensions() {
		return session.getNegotiatedExtensions();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#isSecure()
	 */
	@Override
	public boolean isSecure() {
		return session.isSecure();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#isOpen()
	 */
	@Override
	public boolean isOpen() {
		return session.isOpen();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getMaxIdleTimeout()
	 */
	@Override
	public long getMaxIdleTimeout() {
		return session.getMaxIdleTimeout();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#setMaxIdleTimeout(long)
	 */
	@Override
	public void setMaxIdleTimeout(long milliseconds) {
		session.setMaxIdleTimeout(milliseconds);
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#setMaxBinaryMessageBufferSize(int)
	 */
	@Override
	public void setMaxBinaryMessageBufferSize(int length) {
		session.setMaxBinaryMessageBufferSize(length);
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getMaxBinaryMessageBufferSize()
	 */
	@Override
	public int getMaxBinaryMessageBufferSize() {
		return session.getMaxBinaryMessageBufferSize();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#setMaxTextMessageBufferSize(int)
	 */
	@Override
	public void setMaxTextMessageBufferSize(int length) {
		session.setMaxTextMessageBufferSize(length);
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getMaxTextMessageBufferSize()
	 */
	@Override
	public int getMaxTextMessageBufferSize() {
		return session.getMaxTextMessageBufferSize();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getAsyncRemote()
	 */
	@Override
	public Async getAsyncRemote() {
		return session.getAsyncRemote();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getBasicRemote()
	 */
	@Override
	public Basic getBasicRemote() {
		return session.getBasicRemote();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getId()
	 */
	@Override
	public String getId() {
		return session.getId();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#close()
	 */
	@Override
	public void close() throws IOException {
		session.close();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#close(javax.websocket.CloseReason)
	 */
	@Override
	public void close(CloseReason closeReason) throws IOException {
		session.close(closeReason);
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getRequestURI()
	 */
	@Override
	public URI getRequestURI() {
		return session.getRequestURI();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getRequestParameterMap()
	 */
	@Override
	public Map<String, List<String>> getRequestParameterMap() {
		return session.getRequestParameterMap();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getQueryString()
	 */
	@Override
	public String getQueryString() {
		return session.getQueryString();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getPathParameters()
	 */
	@Override
	public Map<String, String> getPathParameters() {
		return session.getPathParameters();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getUserProperties()
	 */
	@Override
	public Map<String, Object> getUserProperties() {
		return session.getUserProperties();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getUserPrincipal()
	 */
	@Override
	public Principal getUserPrincipal() {
		return session.getUserPrincipal();
	}

	/**
	 * <br>
	 * 2014年2月20日 下午5:48:57
	 * 
	 * @see javax.websocket.Session#getOpenSessions()
	 */
	@Override
	public Set<Session> getOpenSessions() {
		return session.getOpenSessions();
	}

}
