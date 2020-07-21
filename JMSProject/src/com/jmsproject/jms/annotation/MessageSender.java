package com.jmsproject.jms.annotation;

import java.util.Properties;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Resource injection works only in a managed environment, such as a Java EE
 * application server, or a Spring container, for instance. In a standalone
 * application JNDI is your only choice.
 * 
 * Annotations in general are meant to be processed by some tool/framework, and
 * plain JVM that executes your main() method simply does not contain such. The
 * only annotations I know of that are processed by JVM out of the box are
 * compile-time @Deprecated, @Override and @SuppressWarnings.
 * 
 * @author Administrator
 *
 */
public class MessageSender {

	@Resource(name = "jms/ConnectionFactory")
	//@Resource(mappedName = "jms/ConnectionFactory")
	private static ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/MyQueue")
	private static Queue queue;

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();

		sendMessage("Test Annotation");
	}

	public static void sendMessage(String message) {
		MessageProducer messageProducer;
		TextMessage textMessage;
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
			textMessage = session.createTextMessage();
			// textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);

			textMessage.setText(message);
			messageProducer.send(textMessage);

			messageProducer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}