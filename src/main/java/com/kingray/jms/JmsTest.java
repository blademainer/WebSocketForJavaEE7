/**
 * WebSocketForJavaEE7
 */
package com.kingray.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月21日 上午11:09:43
 */
public class JmsTest {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:7777");
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			TextMessage message = session.createTextMessage("userName");
			
			Queue queue = new ActiveMQQueue("testQueue");
			MessageProducer producer = session.createProducer(queue);
			
			producer.send(message);
			
			System.out.println("Message sent!");
			
			
			ConnectionFactory connectionFactoryClient = new ActiveMQConnectionFactory("failover://tcp://10.188.192.169:7777");
			Connection connectionClient = connectionFactoryClient.createConnection();
			connection.start();
			
			Session sessionClient = connectionClient.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = sessionClient.createConsumer(queue);
			Message recvMessage = consumer.receive();
			System.out.println(recvMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
