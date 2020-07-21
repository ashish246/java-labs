package com.jmsproject.jms.pooling.pool;

import javax.jms.Connection;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.pooling.factory.JMSMessageConnectionFactory;
import com.jmsproject.jms.pooling.resources.JMSQueueConnection;
import com.jmsproject.jms.pooling.resources.JMSQueueSession;

/**
 * This class is responsible for maintaining the pool of connection & session.
 * It manages the creation & removal of {@link JMSQueueConnection} &
 * {@link JMSQueueSession} instances which holds the {@link Connection} &
 * {@link Session} respectively.
 * 
 * @author Ashish Tripathi
 * 
 */
public class QueuePoolMgr {
	private static final Logger Logger = LoggerFactory
			.getLogger(QueuePoolMgr.class);

	public QueuePoolMgr() {
	}

	/**
	 * If exists, this will return the {@link JMSQueueConnection} object from
	 * the pool which holds the actual {@link Connection} object, otherwise it
	 * will create a new connection object and add it to the pool.
	 * 
	 * @param pJMSMessageConnFactory
	 * @param pDocumentNO
	 * @return instance of {@link JMSQueueConnection}
	 */
	public static JMSQueueConnection getPooledConnection(
			JMSMessageConnectionFactory pJMSMessageConnFactory,
			String pDocumentNO) {
		JMSQueueConnection tQueueConn = QueueConnectionPool.getInstance()
				.getAvailableConnection(
						pJMSMessageConnFactory.getMaxSessionsPerConnection());

		if (tQueueConn != null) {
			Logger.info(
					"Connection was successfully fetched from the connection pool for the order id: {}",
					new Object[] { pDocumentNO });

			return tQueueConn;
		}

		// if Queue connection is NULL
		try {
			Connection tConn = pJMSMessageConnFactory.getConnection();

			Logger.info(
					"New connection to the queue was successfully created for the order id: {}",
					new Object[] { pDocumentNO });

			// add this new JMS connection to connection pool
			tQueueConn = new JMSQueueConnection(tConn);
			QueueConnectionPool.getInstance().addConnection(tQueueConn);

			return tQueueConn;
		} catch (Exception e) {
			Logger.error(
					"Exception occured while creating connection the queue for the order id: {}",
					new Object[] { pDocumentNO }, e.getMessage(), e);

			return null;
		}

	}

	/**
	 * If exists, this will return the {@link JMSQueueSession} object from the
	 * session pool of the given {@link JMSQueueConnection} object and it holds
	 * the actual {@link Session} object, otherwise it will create a new session
	 * object and add it to the session pool of the given connection object.
	 * 
	 * @param pDocumentNO
	 * @param JMSQueueConnection
	 * @return instance of {@link JMSQueueConnection}
	 */
	public static JMSQueueSession getPooledSession(String pDocumentNO,
			JMSQueueConnection pJMSQueueConnection) {
		QueueSessionPool tSessionPool = QueueConnectionPool.getInstance()
				.getSessionPoolForConnection(pJMSQueueConnection);

		// if there is no session pool
		if (tSessionPool == null) {
			return null;
		}

		JMSQueueSession tQueueSession = tSessionPool.getAvailableSession();

		// if pool has available queue session
		if (tQueueSession != null) {
			Logger.info(
					"Session was successfully fetched from the session pool for the order id: {}",
					new Object[] { pDocumentNO });

			return tQueueSession;
		}

		// create a new session, if queue session returned from pool in NULL.
		try {
			Session tSession = pJMSQueueConnection.getConnection()
					.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Logger.info(
					"New session to the queue was successfully created for the order id: {}",
					new Object[] { pDocumentNO });

			tQueueSession = new JMSQueueSession(tSession);
			tSessionPool.addSession(tQueueSession);

			return tQueueSession;
		} catch (Exception e) {
			Logger.error(
					"Exception occured while creating Session the queue for the order id: {}",
					new Object[] { pDocumentNO }, e.getMessage(), e);

			return null;
		}
	}

	/**
	 * Releases the given {@link JMSQueueConnection} and {@link JMSQueueSession}
	 * back into the pool.
	 * 
	 * @param pJMSQueueConnection
	 * @param pJMSQueueSession
	 */
	public static void releaseConnectionAndSession(
			JMSQueueConnection pJMSQueueConnection,
			JMSQueueSession pJMSQueueSession) {
		if (pJMSQueueConnection == null || pJMSQueueSession == null) {
			return;
		}

		QueueConnectionPool.getInstance().releaseSessionForConnection(
				pJMSQueueConnection, pJMSQueueSession);
	}

	/**
	 * Closes all the {@link JMSQueueConnection}s and {@link JMSQueueSession}s
	 * objects which exists in the pool.
	 */
	public static void destroy() {
		QueueConnectionPool.getInstance().destroy();
	}

	/**
	 * Closes all the {@link JMSQueueConnection}s and {@link JMSQueueSession}s
	 * objects which exists in the pool and whose creation date is older by
	 * 'pMinutes' from current date.
	 * 
	 * @param pMinutes
	 *            , in minutes
	 */
	public static void cleanUp(int pMinutes) {
		QueueConnectionPool.getInstance().cleanUp(pMinutes);
	}

	/**
	 * log count & state of remaining JMS connections & session, after the clean
	 * up
	 * 
	 */
	public static void logResources() {
		QueueConnectionPool.getInstance().logCurrentResources();
	}
}
