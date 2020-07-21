package com.jmsproject.jms.receiver;
import java.io.IOException;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class ReceiverJMSJNDI {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReceiverJMSJNDI.class);
	
    public ReceiverJMSJNDI() {
 
    }
 
    public void receiveMessage() {
    	Connection connection = null;
        try {
        	
        	Properties props = new Properties();
    		/*props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
    		props.setProperty(Context.PROVIDER_URL,"tcp://hostname:61616");*/
            /*try {
    			props.load(ClassLoader
    					.getSystemResourceAsStream("resources/jndi.properties"));
    		} catch (IOException e2) {
    			e2.printStackTrace();
    		}*/
    		
    		Context jndiContext = null;
    		ConnectionFactory connectionFactory = null;
    		Session session = null;
    		Destination destination = null;
    		MessageConsumer consumer = null;
    		
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
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(destination);
            Message message = consumer.receive();
 
            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                System.out.println("Message received is : " + text.getText());
            }
        } catch (JMSException e) {
                      e.printStackTrace();
        }finally {
		    if (connection != null) {
		        try {
		            connection.close();
		        } catch (JMSException e) {
		        }
		    }
        }
    }
 
    public static void main(String[] args) {
        ReceiverJMSJNDI receiver = new ReceiverJMSJNDI();
        receiver.receiveMessage();
    }
}