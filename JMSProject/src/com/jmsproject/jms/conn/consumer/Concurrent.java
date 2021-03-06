package com.jmsproject.jms.conn.consumer;

import java.util.Properties;

import javax.jms.ConnectionConsumer;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

/**
 * http://www.novell.com/documentation/extend52/Docs/help/MP/jms/tutorial/
 * connConsumer-1.htm
 * 
 * 
 * @author Administrator
 *
 */
public class Concurrent {
	public static void main(String[] args) throws Exception {
		
		 Properties props = new Properties();
		// get the initial context
		InitialContext ctx = new InitialContext();

		// lookup the topic object
		Topic topic = (Topic) ctx.lookup("MyTopic");

		// lookup the topic connection factory
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx
				.lookup("ConnectionFactory");

		// create a topic connection
		TopicConnection topicConn = connFactory.createTopicConnection();

		// create a server session pool
		MyServerSessionPool ssPool = new MyServerSessionPool(topicConn);
		topicConn.setExceptionListener(ssPool);

		// create a topic connection consumer
		ConnectionConsumer connConsumer = topicConn.createConnectionConsumer(
				topic, null, ssPool, 10);

		// start the connection
		topicConn.start();

		// send some messages to the newly created connection consumer
		int ackMode = Session.AUTO_ACKNOWLEDGE;
		TopicSession session = topicConn.createTopicSession(false, ackMode);
		TopicPublisher publisher = session.createPublisher(topic);

		Message msg = session.createMessage();

		for (int i = 0; i < 100; i++) {
			publisher.publish(msg);
		}

		System.out.println("sent 1000 messages");

		publisher.close();

		// wait for connection consumer
		while (true) {
			Thread.sleep(10000);
		}
	}
}