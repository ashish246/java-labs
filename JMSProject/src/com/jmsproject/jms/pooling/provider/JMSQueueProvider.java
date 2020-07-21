package com.jmsproject.jms.pooling.provider;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.pooling.sender.JMSMessageSender;

public class JMSQueueProvider {

	private static final Logger Logger = LoggerFactory
			.getLogger(JMSQueueProvider.class);

	// properties that need setting prior to use
	private String mQueueName;

	// JMS resources forming the state of this object
	private Destination mQueue;
	private Session mSession;
	private MessageProducer mProducer;

	public JMSQueueProvider(Destination pQueue, String pQueueName) {
		mQueue = pQueue;
		mQueueName = pQueueName;
	}

	/**
	 * Start the JMS session to process the batch
	 * 
	 * @throws JMSException
	 */
	public void startSession(Session tQueueSession) throws JMSException {
		mSession = tQueueSession;

		if (mQueue == null) {
			mQueue = mSession.createQueue(mQueueName);

			Logger.info(
					"Queue was successfully created for the queue name : {}",
					new Object[] { mQueueName });
		}

		mProducer = mSession.createProducer(mQueue);
		mProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	/**
	 * Close session/producer once the batch has been processed
	 * 
	 * @throws JMSException
	 */
	public void closeProducer() throws JMSException {

		if (mProducer != null) {
			mProducer.close();
		}
		mProducer = null;
	}

	/**
	 * Send the message to the queue using JMSMessageSender
	 * 
	 * @param pMessage
	 * @throws JMSException
	 */
	public void sendMessage(String pMessage) throws JMSException {
		JMSMessageSender tJMSMessageSender = new JMSMessageSender(mSession,
				mProducer);

		tJMSMessageSender.sendAsMessage(pMessage);
	}

	/**
	 * @param pQueue
	 */
	public void setQueue(Destination pQueue) {
		mQueue = pQueue;
	}

	public Session getSession() {
		return mSession;
	}

}
