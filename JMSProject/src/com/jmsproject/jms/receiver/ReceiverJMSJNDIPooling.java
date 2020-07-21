package com.jmsproject.jms.receiver;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class ReceiverJMSJNDIPooling {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReceiverJMSJNDIPooling.class);
	
    public ReceiverJMSJNDIPooling() {
 
    }
 
    public void receiveMessage() {
    	QueueConnection connection = null;
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
    		QueueConnectionFactory connectionFactory = null;
    		QueueSession session = null;
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
    		    connectionFactory = (QueueConnectionFactory)jndiContext.lookup("QueueConnectionFactory");
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
            connection = connectionFactory.createQueueConnection();
            connection.start();
            
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            
            consumer = session.createConsumer(destination);
            
            Message message = consumer.receive();
 
            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                System.out.println("Message received is : " + text.getText());
            }
            
            consumer.close();
            
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
        ReceiverJMSJNDIPooling receiver = new ReceiverJMSJNDIPooling();
        receiver.receiveMessage();
    }
}