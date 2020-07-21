package com.jmsproject.jms.sender;

import java.util.Properties;

import javax.jms.ConnectionConsumer;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.ServerSession;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class SenderJMSJNDIPooling {
 
	private static final Logger LOG = LoggerFactory.getLogger(SenderJMSJNDIPooling.class);
 
    public SenderJMSJNDIPooling() {
 
    }
 
    public void sendMessage() {
 
        Properties props = new Properties();
		
		Context jndiContext = null;
		QueueConnectionFactory connectionFactory = null;
		QueueConnection connection = null;
		QueueSession session = null;
		Destination destination = null;
		MessageProducer producer = null;
		
		try {
			jndiContext = new InitialContext();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		
		/*
		 * Look up connection factory and destination.
		 */
		try {
		    connectionFactory = (QueueConnectionFactory)jndiContext.lookup("ConnectionFactory");
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
		try {
		    connection = connectionFactory.createQueueConnection();
		    
		 // create a server session pool
		      MyServerSessionPool ssPool = new MyServerSessionPool(connection);
		      connection.setExceptionListener(ssPool);
		      
		   // create a topic connection consumer
		      ConnectionConsumer connConsumer =
		      connection.createConnectionConsumer(destination, null, ssPool, 10);
		      
		   // start the connection
		      connection.start();
		    
		    session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		    
		    producer = session.createProducer(destination);
		    
		    TextMessage message = session.createTextMessage();
		    
		    message.setText("This is JNDI text message ");
		    
		    LOG.info("Sending message: 1 - " + message.getText());
		    System.out.println("Sending message: 1 - " + message.getText());
		    
		    producer.send(message);
		    
		    producer.close();
    
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
        SenderJMSJNDIPooling sender = new SenderJMSJNDIPooling();
        sender.sendMessage();
    }
    
    
/**
This is a simple implementation of a server session pool
which does not manage a pool but merely created a new server
session each time it is called.
*/
    
	public class MyServerSessionPool
	implements ServerSessionPool, ExceptionListener
	{
		private final QueueConnection _conn;
		    
		MyServerSessionPool(QueueConnection conn)
		{
		   _conn = conn;
		}
		    
		public ServerSession getServerSession()
		{
		  return new MyServerSession(_conn);
		}
		    
		public void onException(JMSException ex)
		{
		   ex.printStackTrace();
		}
	}
	
	
	/**
	   This is a very simple implementation of a server session,
	   which creates a new thread for performing asynchronous message
	   processing each time it is called.
	 */
	                                                                           
	public class MyServerSession implements ServerSession
	{
	    private final QueueConnection _conn;
	    private       QueueSession    _topicSession;
	                                                                           
	    MyServerSession(QueueConnection conn)
	    {
	       _conn = conn;
	    }
	                                                                           
	    // get or create the session for this server session
	    // when creating a session a message listener is set
	    public synchronized Session getSession() throws JMSException
	    {
	       if (_topicSession == null) {
	          _topicSession = _conn.createQueueSession(false,
	              Session.AUTO_ACKNOWLEDGE);
	          MessageListener listener;
	          listener = new MyMessageListener(_topicSession);
	          _topicSession.setMessageListener(listener);
	       }
	                                                                          
	       return _topicSession;
	    }
	                                                                           
	    public void start() throws JMSException
	    {
	       Thread t = new Thread(_topicSession);
	       t.start();
	    }
	                                                                           
	}
	
	// a simple message listener that counts 100 messages
    static class MyMessageListener implements MessageListener
    {
    	 static int _msgCount = 0;
       private final QueueSession _topicSession;
                                                                          
       MyMessageListener(QueueSession topicSession)
       {
          _topicSession = topicSession;
       }
                                                                          
       // must be thread-safe
       public void onMessage(Message msg)
       {
         if ( (_msgCount%100) == 0)
          {
             System.out.print(".");
          }
                                                                         
          if (++_msgCount == 1000)
          {
            System.out.println("done");
             System.exit(0);
          }
       }
    }
 
}