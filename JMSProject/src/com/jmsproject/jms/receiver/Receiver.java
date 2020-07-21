package com.jmsproject.jms.receiver;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class Receiver {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;
 
    public Receiver() {
 
    }
 
    public void receiveMessage() {
        try {
            factory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            
            //set time out parameters
            if(factory instanceof ActiveMQConnectionFactory){
            	
            	factory = (ActiveMQConnectionFactory)factory;
            	
            	((ActiveMQConnectionFactory) factory).setWarnAboutUnstartedConnectionTimeout(5);
            	((ActiveMQConnectionFactory) factory).setSendTimeout(5);
            	((ActiveMQConnectionFactory) factory).setCloseTimeout(5);
            }
            
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("SAMPLEQUEUE");
            consumer = session.createConsumer(destination);
            Message message = consumer.receive();
 
            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                System.out.println("Message is : " + text.getText());
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
        Receiver receiver = new Receiver();
        receiver.receiveMessage();
    }
}