package com.jmsproject.jms.pooling.factory;

import javax.jms.Connection;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.pooling.pool.QueuePoolMgr;
import com.jmsproject.jms.pooling.resources.JMSQueueConnection;
import com.jmsproject.jms.pooling.resources.JMSQueueSession;

/**
 * This manager class handles the creation of JMS connection and session or
 * fetches the connection & session from the pool if pooling is enabled. One
 * instance of this class will hold the object of {@link JMSQueueConnection} and
 * {@link JMSQueueSession} which are being used by this thread.
 * 
 * @author Ashish Tripathi
 * 
 */
public class QueueConnectionMgr {
	private static final Logger Logger = LoggerFactory
			.getLogger(QueueConnectionMgr.class);

	private JMSQueueConnection mJMSQueueConnection;

	private JMSQueueSession mJMSQueueSession;

	private JMSMessageConnectionFactory mJMSMessageConnFactory;

	private boolean isConnectionPoolEnabled;

	private String mDocumentNO;

	public QueueConnectionMgr(
			JMSMessageConnectionFactory pJMSMessageConnFactory,
			String pDocumentNO) {
		mJMSMessageConnFactory = pJMSMessageConnFactory;
		mDocumentNO = pDocumentNO;

		isConnectionPoolEnabled = pJMSMessageConnFactory
				.isJmsConnectionPoolingEnabled();
	}

	/**
	 * Returns an available session from either pool, if pooling enabled or by
	 * creating a new for each thread. It first fetches/creates a connection and
	 * then a session from that connection.
	 * 
	 * @return {@link Session}, which will be used to send the message to the
	 *         queue.
	 */
	public Session getAvailableQueueSession() {
		mJMSQueueConnection = getActiveJMSConnection();

		if (mJMSQueueConnection == null
				|| mJMSQueueConnection.getConnection() == null) {
			Logger.warn(
					"Queue connection could not be found for the order id: {} ",
					new Object[] { mDocumentNO });
			return null;
		}

		// get session object from connection.
		mJMSQueueSession = getActiveJMSSession();

		if (mJMSQueueSession != null) {
			mJMSQueueConnection.setSessionInUse(true);
		}

		return mJMSQueueSession.getSession();
	}

	/**
	 * If pooling is enabled, it will return the connection object from the pool
	 * else a newly created connection object.
	 * 
	 * @return the instance of {@link JMSQueueConnection} which holds the
	 *         {@link Connection} object.
	 */
	private JMSQueueConnection getActiveJMSConnection() {
		// if connection pooling is enabled
		if (isConnectionPoolEnabled) {
			// get connection from pool
			return QueuePoolMgr.getPooledConnection(mJMSMessageConnFactory,
					mDocumentNO);
		}

		// create a new connection, if pooling is not enabled.
		try {
			Connection tConnection = mJMSMessageConnFactory.getConnection();

			Logger.info(
					"New connection to the queue was successfully created for the order id: {}",
					new Object[] { mDocumentNO });

			return new JMSQueueConnection(tConnection);
		} catch (Exception e) {
			Logger.error(
					"Exception occured while creating connection the queue for the order id: {}",
					new Object[] { mDocumentNO }, e.getMessage(), e);

			return null;
		}
	}

	/**
	 * If pooling is enabled, it will return the session object from the pool
	 * else a newly created session object.
	 * 
	 * @return the instance of {@link JMSQueueSession} which holds the
	 *         {@link Session} object.
	 */
	private JMSQueueSession getActiveJMSSession() {
		// if connection pooling is enabled
		if (isConnectionPoolEnabled) {
			// get connection from pool
			return QueuePoolMgr.getPooledSession(mDocumentNO,
					mJMSQueueConnection);
		}

		// create a new session, if pooling is not enabled.
		try {
			Session tSession = mJMSQueueConnection.getConnection()
					.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Logger.info(
					"New session to the queue was successfully created for the order id: {}",
					new Object[] { mDocumentNO });

			return new JMSQueueSession(tSession);
		} catch (Exception e) {
			Logger.error(
					"Exception occured while creating Session the queue for the order id: {}",
					new Object[] { mDocumentNO }, e.getMessage(), e);

			return null;
		}
	}

	/**
	 * If connection pooling is enabled then it will release instance of
	 * {@link JMSQueueConnection} and {@link JMSQueueSession} to the pool, else
	 * it will close the {@link Connection} & {@link Session} held by these
	 * instances.
	 */
	public void releaseQueueResources() {
		// if connection pooling is enabled
		if (isConnectionPoolEnabled) {
			QueuePoolMgr.releaseConnectionAndSession(mJMSQueueConnection,
					mJMSQueueSession);

			return;
		}

		// if connection pooling is not enabled, close session & connections for
		// current thread
		try {
			mJMSQueueSession.close();
			mJMSQueueSession = null;

			mJMSQueueConnection.close();
			mJMSQueueConnection.setSessionInUse(false);
			mJMSQueueConnection = null;
		} catch (Exception e) {
			Logger.error(
					"Exception occured while releasing session & connecions for the order id: {}",
					new Object[] { mDocumentNO }, e.getMessage(), e);
		}
	}
}
