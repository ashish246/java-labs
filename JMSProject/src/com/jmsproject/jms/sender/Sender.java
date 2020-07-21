package com.jmsproject.jms.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class Sender {
 
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;
 
    public Sender() {
 
    }
 
    public void sendMessage() {
 
        try {
            factory = new ActiveMQConnectionFactory("failover://tcp://localhost:61616");
            connection = factory.createConnection();
            
          //set time out parameters
            if(factory instanceof ActiveMQConnectionFactory){
            	
            	factory = (ActiveMQConnectionFactory)factory;
            	
            	((ActiveMQConnectionFactory) factory).setWarnAboutUnstartedConnectionTimeout(0L);
            	((ActiveMQConnectionFactory) factory).setSendTimeout(5000);
            	((ActiveMQConnectionFactory) factory).setCloseTimeout(5000);
            }
            
            //connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("SAMPLEQUEUE");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message = session.createTextMessage();
            message.setText("Hello ...This is a sample message..sending from FirstClient");
            producer.send(message);
            System.out.println("Sent: " + message.getText());
 
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
        Sender sender = new Sender();
        sender.sendMessage();
    }
 
}