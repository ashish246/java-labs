package com.jmsproject.jms.conn.consumer;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ServerSession;
import javax.jms.ServerSessionPool;
import javax.jms.TopicConnection;

/**
 * /** This is a simple implementation of a server session pool which does not
 * manage a pool but merely created a new server session each time it is called.
 */
public class MyServerSessionPool implements ServerSessionPool,
		ExceptionListener {
	private final TopicConnection _conn;

	MyServerSessionPool(TopicConnection conn) {
		_conn = conn;
	}

	public ServerSession getServerSession() {
		return new MyServerSession(_conn);
	}

	public void onException(JMSException ex) {
		ex.printStackTrace();
	}
}