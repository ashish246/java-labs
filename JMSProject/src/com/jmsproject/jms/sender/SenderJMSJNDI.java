package com.jmsproject.jms.sender;

import java.io.IOException;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class SenderJMSJNDI {
 
	private static final Logger LOG = LoggerFactory.getLogger(SenderJMSJNDI.class);
 
    public SenderJMSJNDI() {
 
    }
 
    public void sendMessage() {
 
        Properties props = new Properties();
		/*props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,"tcp://hostname:61616");*/
        /*try {
			props.load(ClassLoader.getSystemResourceAsStream("resources/jndi.properties"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}*/
		
		Context jndiContext = null;
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		Destination destinationSOM = null;
		MessageProducer producer = null;
		MessageProducer producerSOM = null;
		
		try {
			jndiContext = new InitialContext();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		
		/*
		 * Look up connection factory and destination.
		 */
		try {
		    connectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
		    destination = (Destination)jndiContext.lookup("MyQueue");
		    destinationSOM = (Destination)jndiContext.lookup("MyQueueSOM");
		} catch (NamingException e) {
		    LOG.info("JNDI API lookup failed: " + e);
		    System.out.println("JNDI API lookup failed: " + e);
		}
		
		/*
		 * Create connection. Create session from connection; false means
		 * session is not transacted. Create sender and text message. Send
		 * messages, varying text slightly. Send end-of-messages message.
		 * Finally, close connection.
		 */
		try {
		    connection = connectionFactory.createConnection();
		    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    producer = session.createProducer(destination);
		    
		    producerSOM = session.createProducer(destinationSOM);
		    
		    TextMessage message = session.createTextMessage();
		    message.setText("This is JNDI text message ");
		    LOG.info("Sending message: " + message.getText());
		    System.out.println("Sending message: " + message.getText());
		    
		    producer.send(message);
		    
		    TextMessage messageSOM = session.createTextMessage();
		    messageSOM.setText("This is JNDI text message for SOM ");
		    LOG.info("Sending message to SOM: " + messageSOM.getText());
		    
		    producerSOM.send(messageSOM);
    
		} catch (JMSException e) {
		    LOG.info("Exception occurred: " + e);
		    System.out.println("Exception occurred: " + e);
		} finally {
		    if (connection != null) {
		        try {
		            connection.close();
		        } catch (JMSException e) {
		        }
		    }
		}
    }
 
    public static void main(String[] args) {
        SenderJMSJNDI sender = new SenderJMSJNDI();
        sender.sendMessage();
    }
 
}