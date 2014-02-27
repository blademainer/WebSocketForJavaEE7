/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.app.login;

import com.kingray.websocket.vo.VO;
import com.xiongyingqi.util.EntityHelper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午8:21:22
 */
public class LoginVoReturn extends EntityHelper implements VO {
	public static LoginVoReturn LOGIN_SUCCESS = new LoginVoReturn(1, "LOGIN_SUCCESS", "登录成功.");
	public static LoginVoReturn LOGIN_LOGINFAIL = new LoginVoReturn(0, "LOGIN_LOGINFAIL", "登录失败.");
	public static LoginVoReturn LOGIN_SERVER_ERRO = new LoginVoReturn(2, "LOGIN_SERVER_ERRO", "内部服务器错误.");
	
	
	/**
	 * 2014年2月26日 下午4:14:45
	 * long LoginVoReturn.java
	 */
	private static final long serialVersionUID = 8931359588940852981L;
	
	private int statusCode;
	private String statusName;
	private String statusValue;
	
	public LoginVoReturn(){}
	public LoginVoReturn(int statusCode, String statusName, String statusValue){
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.statusValue = statusValue;
		
	}
	
	/**
	 * int
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * int
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * String
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * String
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * String
	 * @return the statusValue
	 */
	public String getStatusValue() {
		return statusValue;
	}
	/**
	 * String
	 * @param statusValue the statusValue to set
	 */
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

}
