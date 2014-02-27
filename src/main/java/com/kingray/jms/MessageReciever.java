/**
 * WebSocketForJavaEE7
 */
package com.kingray.jms;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午7:40:31
 */
import java.util.*;

import javax.naming.*;
import javax.jms.*;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

public class MessageReciever implements MessageListener {
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("Message content is:" + textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		MessageReciever msgRcvr = new MessageReciever();
		boolean transacted = false;
		int acknowledgementMode = Session.AUTO_ACKNOWLEDGE;
		try {
//			Context context = new InitialContext(properties);
//			Object obj = context.lookup(queueConnectionFactoryName);
			QueueConnectionFactory queueConnectionFactory = new ActiveMQConnectionFactory("vm://127.0.0.1");

//			obj = context.lookup(queueName);
//			Topic topic = new ActiveMQTopic("test");
			Queue queue = new ActiveMQQueue("test");

			QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
			queueConnection.start();
			QueueSession queueSession = queueConnection.createQueueSession(transacted,
					acknowledgementMode);
			QueueReceiver queueReceiver = queueSession.createReceiver(queue);

			queueReceiver.setMessageListener(msgRcvr);

			synchronized (msgRcvr) {
				msgRcvr.wait(100000);
			}

			if (queueReceiver != null) {
				queueReceiver.close();
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
