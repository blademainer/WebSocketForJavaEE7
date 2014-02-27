/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解Application用于指定该application作为websocket的处理器<br>
 * 默认值为以首字母为小写的类名<br>
 * 如TestApplication默认注解值为testApplication<br>
 * 
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月25日 上午11:58:08
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebSocketApplication {
	/**
	 * 指定该application的ID，如果不指定该值，那么默认值为以首字母为小写的类名<br>
	 * 如TestApplication默认注解值为testApplication <br>
	 * 2014年2月25日 下午12:01:00
	 * 
	 * @return
	 */
	public String value() default "";
}
