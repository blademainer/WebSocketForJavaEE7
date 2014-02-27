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
public class LoginVo extends EntityHelper implements VO {
	/**
	 * 2014年2月24日 下午8:22:56
	 * long LoginVo.java
	 */
	private static final long serialVersionUID = 1288542554763511304L;
	
	private String userName;
	private String password;
	/**
	 * String
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * String
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * String
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * String
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
