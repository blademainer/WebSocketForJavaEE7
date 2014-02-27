/**
 * WebSocketForJavaEE7
 */
package com.kingray.jms;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午7:39:06
 */

import javax.jms.*;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class MessageProducter {
	public static void main(String[] args) {

		boolean transacted = false;//transaction模式
		int acknowledgementMode = Session.AUTO_ACKNOWLEDGE;//acknowledgement模式
		String message = "Message need to send";//模拟需要发送的消息

//		Properties properties = new Properties();
//		properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
//		properties.put(Context.PROVIDER_URL, "t3://localhost:7001");

		try {
//			Context context = new InitialContext(properties);
//			Object obj = context.lookup(queueConnectionFactoryName);
//			QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) obj;//JMS Connection Factory的获得
			QueueConnectionFactory queueConnectionFactory = new ActiveMQConnectionFactory("vm://127.0.0.1");
			
//			obj = context.lookup(queueName);
//			Queue queue = (Queue) obj;//JMS Queue或者JMS Topic的获得
			Queue queue = new ActiveMQQueue("test");

			QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();//产生连接
			queueConnection.start();
			QueueSession queueSession = queueConnection.createQueueSession(transacted,
					acknowledgementMode);
			TextMessage textMessage = queueSession.createTextMessage();
			textMessage.clearBody();
			textMessage.setText(message);
			QueueSender queueSender = queueSession.createSender(queue);
			queueSender.send(textMessage);
			if (transacted) {
				queueSession.commit();
			}

			if (queueSender != null) {
				queueSender.close();
			}
			if (queueSession != null) {
				queueSession.close();
			}
			if (queueConnection != null) {
				queueConnection.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
