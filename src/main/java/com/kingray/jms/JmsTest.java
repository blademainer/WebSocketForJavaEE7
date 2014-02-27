/**
 * WebSocketForJavaEE7
 */
package com.kingray.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月21日 上午11:09:43
 */
public class JmsTest {
	public static void main(String[] args) throws Exception {
//		main2(args);
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:7788");
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			final TextMessage message = session.createTextMessage("userName");
			
			Topic topic = new ActiveMQTopic("testTopic");
			Queue queue = new ActiveMQQueue("testQueue");
			final MessageProducer producer = session.createProducer(topic);
			producer.send(message);
//			new Thread(){
//				/**
//				 * <br>2014年2月24日 下午5:48:50
//				 * @see java.lang.Thread#run()
//				 */
//				@Override
//				public void run() {
//					for (int i = 0; i < 100; i++) {
//						try {
//							try {
//								producer.send(message);
//								Thread.sleep(10);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//						} catch (JMSException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}.start();
			
			System.out.println("Message sent!");
			
			Queue queueClient = new ActiveMQQueue("clientQueue");
			Queue topicClient = new ActiveMQQueue("testTopic");
			ConnectionFactory connectionFactoryClient = new ActiveMQConnectionFactory("tcp://localhost:7788");
			Connection connectionClient = connectionFactoryClient.createConnection();
			connectionClient.start();
			
			Session sessionClient = connectionClient.createSession(true, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = sessionClient.createConsumer(topicClient);
			Message recvMessage = consumer.receive();
			System.out.println(recvMessage);
			consumer.setMessageListener(new MessageListener(){
	            @Override
	            public void onMessage(Message m) {
	                TextMessage textMsg = (TextMessage) m;
	                try {
	                    System.out.println(textMsg.getText());
	                } catch (JMSException e) {
	                    e.printStackTrace();
	                }
	            }
	           
	        });
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	public static void main2(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
   
        Connection connection = factory.createConnection();
        connection.start();
       
        Queue queue = new ActiveMQQueue("testQueue");
       
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Message message = session.createTextMessage("Hello JMS!");
       
        MessageProducer producer = session.createProducer(queue);
        producer.send(message);
   
        System.out.println("Send Message Completed!");
       
        MessageConsumer comsumer = session.createConsumer(queue);
        Message recvMessage = comsumer.receive();
        System.out.println(((TextMessage)recvMessage).getText());
    }
}
