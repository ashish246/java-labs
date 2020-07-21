package com.jmsproject.jms.annotation;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageReceiver {

	@Resource(mappedName = "jms/JMSConnectionFactory")
	private static ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/myQueue")
	private static Queue myQueue;

	public static void main(String[] args) throws Exception {

		receiveMessage();
	}

	private static String message;

	public static String receiveMessage() {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			QueueBrowser queueBrowser = session.createBrowser(myQueue);
			Enumeration enumeration = queueBrowser.getEnumeration();
			while (enumeration.hasMoreElements()) {
				TextMessage o = (TextMessage) enumeration.nextElement();
				return "Receive " + o.getText();

			}
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return "";
	}
}
