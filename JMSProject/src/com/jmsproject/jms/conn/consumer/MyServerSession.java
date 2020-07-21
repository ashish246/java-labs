package com.jmsproject.jms.conn.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ServerSession;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

/**
 * This is a very simple implementation of a server session, which creates a new
 * thread for performing asynchronous message processing each time it is called.
 */

public class MyServerSession implements ServerSession {
	private final TopicConnection _conn;
	private TopicSession _topicSession;

	MyServerSession(TopicConnection conn) {
		_conn = conn;
	}

	// get or create the session for this server session
	// when creating a session a message listener is set
	public synchronized Session getSession() throws JMSException {
		if (_topicSession == null) {
			_topicSession = _conn.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageListener listener;
			listener = new MyMessageListener(_topicSession);
			_topicSession.setMessageListener(listener);
		}

		return _topicSession;
	}

	public void start() throws JMSException {
		Thread t = new Thread(_topicSession);
		t.start();
	}

	// a simple message listener that counts 100 messages
	static class MyMessageListener implements MessageListener {
		private final TopicSession _topicSession;

		MyMessageListener(TopicSession topicSession) {
			_topicSession = topicSession;
		}

		// must be thread-safe
		public void onMessage(Message msg) {
			if ((_msgCount % 100) == 0) {
				System.out.print("_._" + _msgCount);
			}

			if (++_msgCount == 1000) {
				System.out.println("done_"+_msgCount);
				System.exit(0);
			}
		}
	}

	static int _msgCount = 0;
}
