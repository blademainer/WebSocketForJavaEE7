/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.vo;

import java.io.Serializable;

import javax.websocket.Session;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月20日 下午3:42:34
 */
public class WebSocketVo implements Serializable{
	/**
	 * 2014年2月20日 下午3:45:23
	 * long WebSocketVo.java
	 */
	private static final long serialVersionUID = 2548674468359882645L;
	
	private int id;
	private String type;
	private String userName;
	private String content;
	private Session session;
	
}
