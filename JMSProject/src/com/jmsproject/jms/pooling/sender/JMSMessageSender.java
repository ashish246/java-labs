package com.jmsproject.jms.pooling.sender;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSMessageSender {

	// private final int BUFFER_SIZE = 2048;

	private Session mSession;
	private MessageProducer mProducer;

	public JMSMessageSender(Session pSession, MessageProducer pProducer) {
		mSession = pSession;
		mProducer = pProducer;
	}

	/**
	 * Send message to the queue as a String
	 * 
	 * @param pMessage
	 * @throws JMSException
	 */
	public void sendAsMessage(String pMessage) throws JMSException {
		TextMessage msg = mSession.createTextMessage(pMessage);
		mProducer.send(msg);

	}

	/*
	 * private StringBuffer readAll(Reader reader) { char[] cbuffer = new
	 * char[BUFFER_SIZE]; StringBuffer sbuffer = new StringBuffer();
	 * BufferedReader breader = new BufferedReader(reader); int charsRead; try {
	 * while((charsRead = breader.read(cbuffer)) > 0) { sbuffer.append(cbuffer,
	 * 0, charsRead); } } catch(IOException e) { throw new RuntimeException(e);
	 * } return sbuffer; }
	 */

}
