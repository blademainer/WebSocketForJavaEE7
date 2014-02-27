/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoaderListener;

import com.xiongyingqi.util.EntityHelper;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午8:31:11
 */
//@WebListener
public class SpringContext extends ContextLoaderListener {
	/**
	 * <br>2014年2月24日 下午9:16:56
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		EntityHelper.print("SpringContext init");
		super.contextInitialized(event);
	}
}
