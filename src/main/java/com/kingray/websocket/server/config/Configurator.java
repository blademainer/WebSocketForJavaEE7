/**
 * WebSocketFotTomcat8
 */
package com.kingray.websocket.server.config;

import java.util.List;

import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import com.xiongyingqi.util.EntityHelper;

/**
 * @author 瑛琪 <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2013-11-14 下午4:50:16
 */
public class Configurator extends ServerEndpointConfig.Configurator{
	/**
	 * <br>2013-11-14 下午4:50:53
	 * @see javax.websocket.server.ServerEndpointConfig.Configurator#getEndpointInstance(java.lang.Class)
	 */
	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
		EntityHelper.print(endpointClass);
		return super.getEndpointInstance(endpointClass);
	}
	/**
	 * <br>2013-11-14 下午4:51:47
	 * @see javax.websocket.server.ServerEndpointConfig.Configurator#checkOrigin(java.lang.String)
	 */
	@Override
	public boolean checkOrigin(String originHeaderValue) {
		EntityHelper.print(originHeaderValue);
		return super.checkOrigin(originHeaderValue);
	}
	/**
	 * <br>2013-11-14 下午4:52:04
	 * @see javax.websocket.server.ServerEndpointConfig.Configurator#getNegotiatedExtensions(java.util.List, java.util.List)
	 */
	@Override
	public List<Extension> getNegotiatedExtensions(List<Extension> installed,
			List<Extension> requested) {
		for (Extension extension : installed) {
			EntityHelper.print(extension);
		}
		for (Extension extension : requested) {
			EntityHelper.print(extension);
		}
		return super.getNegotiatedExtensions(installed, requested);
	}
	/**
	 * <br>2013-11-14 下午4:52:38
	 * @see javax.websocket.server.ServerEndpointConfig.Configurator#getNegotiatedSubprotocol(java.util.List, java.util.List)
	 */
	@Override
	public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
		for (String string : supported) {
			EntityHelper.print(string);
		}
		for (String string : requested) {
			EntityHelper.print(string);
		}
		return super.getNegotiatedSubprotocol(supported, requested);
	}
	/**
	 * <br>2013-11-14 下午4:53:08
	 * @see javax.websocket.server.ServerEndpointConfig.Configurator#modifyHandshake(javax.websocket.server.ServerEndpointConfig, javax.websocket.server.HandshakeRequest, javax.websocket.HandshakeResponse)
	 */
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request,
			HandshakeResponse response) {
		EntityHelper.printDetail(sec);
		EntityHelper.printDetail(request);
		EntityHelper.printDetail(response);
		super.modifyHandshake(sec, request, response);
	}
}
