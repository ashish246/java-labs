/**
 * 
 */
package com.jmsproject.jms.pooling.pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.pooling.resources.JMSQueueConnection;
import com.jmsproject.jms.pooling.resources.JMSQueueSession;

/**
 * This class will maintain a pool of {@link JMSQueueConnection} instances, each
 * of which will hold a {@link Connection} object. This connection object will
 * map to a {@link QueueSessionPool} which will hold the {@link JMSQueueSession}
 * objects in the context of that connection.
 * 
 * @author Ashish Tripathi
 * 
 */
public class QueueConnectionPool {
	private static final Logger Logger = LoggerFactory
			.getLogger(QueueConnectionPool.class);
	/**
	 * Map which maintains the pool of {@link JMSQueueConnection} and
	 * {@link QueueSessionPool} within these queue connections.
	 */
	private ConcurrentHashMap<JMSQueueConnection, QueueSessionPool> mConnectionPool;

	private int mTotalConnectionCount;

	QueueConnectionPool() {
		if (mConnectionPool == null) {
			mConnectionPool = new ConcurrentHashMap<>(8, 0.9f, 1);
		}
	}

	/**
	 * @return the instance of {@link QueueConnectionPool} held by
	 *         {@link QueueConnectionPoolHolder}. This will always return the
	 *         same singleton object.
	 */
	public static QueueConnectionPool getInstance() {
		return QueueConnectionPoolHolder.instance;
	}

	// Initialization On Demand Holder idiom
	private static class QueueConnectionPoolHolder {
		static QueueConnectionPool instance = new QueueConnectionPool();
	}

	/**
	 * Returns the instance of {@link JMSQueueConnection} from the connection
	 * pool where:
	 * 
	 * <ul>
	 * <li>{@link QueueSessionPool} mapped to this connection does not have max
	 * allowed sessions per connection.</li>
	 * <li>It has the least number of sessions in the {@link QueueSessionPool}
	 * mapped to it among all the connections in the connection pool.</li>
	 * </ul>
	 * 
	 * @param pMaxSessionsPerConnection
	 * @return instance of {@link JMSQueueConnection}
	 */
	public JMSQueueConnection getAvailableConnection(
			int pMaxSessionsPerConnection) {
		int tMinSessionCount = 0;
		JMSQueueConnection tConnectionWithMinSessions = null;

		for (Map.Entry<JMSQueueConnection, QueueSessionPool> tEntry : mConnectionPool
				.entrySet()) {
			// Max session in a connection can be up to 10.
			if (tEntry.getValue() != null
					&& tEntry.getValue().getTotalSessionCount() < pMaxSessionsPerConnection) {
				// Return connection with minimum number of sessions.
				if (tConnectionWithMinSessions == null) {
					tConnectionWithMinSessions = tEntry.getKey();
					tMinSessionCount = tEntry.getValue().getTotalSessionCount();
				} else if (tEntry.getValue().getTotalSessionCount() < tMinSessionCount) {
					tConnectionWithMinSessions = tEntry.getKey();
					tMinSessionCount = tEntry.getValue().getTotalSessionCount();
				}
			}
		}

		try {
			// restart the connection to validate the object, if none of its
			// sessions are in use.
			if (tConnectionWithMinSessions != null
					&& !tConnectionWithMinSessions.isSessionInUse()) {
				tConnectionWithMinSessions.start();

				return tConnectionWithMinSessions;
			}
		} catch (JMSException e) {
			Logger.error(
					"Exception occured while staring JMS queue connection.",
					e.getMessage(), e);

			// mark the connection object corrupted, if it could not be
			// restarted. It will be removed in the clean up at the end of job
			// run.
			tConnectionWithMinSessions.setCorrupted(true);
		}

		return null;
	}

	/**
	 * Adds an instance of {@link JMSQueueConnection}, which holds the
	 * {@link Connection} object, into the connection pool. This new connection
	 * will have empty session pool. Also increment the count of total number of
	 * connections in the pool.
	 * 
	 * @param pJMSQueueConnection
	 */
	public void addConnection(JMSQueueConnection pJMSQueueConnection) {
		mConnectionPool.put(pJMSQueueConnection, new QueueSessionPool());

		++mTotalConnectionCount;

		Logger.debug(
				"Resource is successfully added into the pool for the connection: {}",
				new Object[] { pJMSQueueConnection });
	}

	/**
	 * Removes the given {@link JMSQueueConnection} object from the connection
	 * pool and close the {@link Connection} object it holds. It destroys the
	 * session pool this connection maps to. Also, decrement the count of total
	 * number of connection in the pool.
	 * 
	 * @param pJMSQueueConnection
	 */
	public void removeConnection(JMSQueueConnection pJMSQueueConnection) {
		try {
			pJMSQueueConnection.close();
		} catch (JMSException e) {
			Logger.error(
					"Exception occured while closing JMS queue connection.",
					e.getMessage(), e);
		}

		// Destroy the session pool it holds.
		mConnectionPool.get(pJMSQueueConnection).destroy();

		mConnectionPool.remove(pJMSQueueConnection);

		--mTotalConnectionCount;

		Logger.debug(
				"Resource is successfully removed from the pool for the connection: {}",
				new Object[] { pJMSQueueConnection });
	}

	/**
	 * Removes all the {@link JMSQueueConnection} objects in the connection pool
	 * and close all the {@link Connection} objects they hold. Set the counter
	 * of total number of connections in the pool to zero.
	 * 
	 */
	public void destroy() {
		for (Map.Entry<JMSQueueConnection, QueueSessionPool> tEntry : mConnectionPool
				.entrySet()) {
			removeConnection(tEntry.getKey());
		}

		mConnectionPool = null;

		mTotalConnectionCount = 0;
	}

	/**
	 * @param pJMSQueueConnection
	 * @return the {@link QueueSessionPool} mapped to given
	 *         {@link JMSQueueConnection}
	 */
	public QueueSessionPool getSessionPoolForConnection(
			JMSQueueConnection pJMSQueueConnection) {
		if (pJMSQueueConnection == null
				|| mConnectionPool.get(pJMSQueueConnection) == null) {
			return null;
		}

		return mConnectionPool.get(pJMSQueueConnection);
	}

	/**
	 * Releases the given {@link JMSQueueSession} back in the session pool
	 * mapped to given {@link JMSQueueConnection}.
	 * 
	 * @param pJMSQueueConnection
	 * @param pJMSQueueSession
	 */
	public void releaseSessionForConnection(
			JMSQueueConnection pJMSQueueConnection,
			JMSQueueSession pJMSQueueSession) {
		QueueSessionPool tSessionPool = mConnectionPool
				.get(pJMSQueueConnection);

		if (tSessionPool == null) {
			return;
		}

		tSessionPool.releaseSession(pJMSQueueSession);

		// check if any session for this connection is locked.
		if (!tSessionPool.hasJMSSessionLocked()) {
			pJMSQueueConnection.setSessionInUse(false);

			Logger.debug(
					"Resource is successfully released into the pool for the connection: {}",
					new Object[] { pJMSQueueConnection });
		}
	}

	/**
	 * @return the total number of connections in the pool.
	 */
	public int getTotalConnectionCount() {
		return mTotalConnectionCount;
	}

	/**
	 * Removes all the {@link JMSQueueConnection} objects from the connection
	 * pool and close the {@link Connection} object it holds, if their creation
	 * date is older by 'pConnExpiryTime' from current date. Also, decrement the
	 * count of total number of connections in the pool for all the connections
	 * removed.
	 * 
	 * @param pConnExpiryTime
	 *            , in minutes
	 */
	public void cleanUp(int pConnExpiryTime) {
		for (Map.Entry<JMSQueueConnection, QueueSessionPool> tEntry : mConnectionPool
				.entrySet()) {
			// clean up the connection, all the session within it will also be
			// removed along with connection.
			if (tEntry.getKey().isOlderThan(pConnExpiryTime)
					|| tEntry.getKey().isCorrupted()) {
				removeConnection(tEntry.getKey());
			} else {
				// clean up the old session for the connection which is still
				// valid and is not expired.
				tEntry.getValue().cleanUp(pConnExpiryTime);

				// check if any session for this connection is locked.
				if (!tEntry.getValue().hasJMSSessionLocked()) {
					tEntry.getKey().setSessionInUse(false);
				}
			}
		}
	}

	/**
	 * This method logs the count of current connections of type
	 * {@link Connection} and sessions of type {@link Session} with its state in
	 * each {@link QueueSessionPool} mapped to that connection.
	 */
	public void logCurrentResources() {
		StringBuilder logMessage = new StringBuilder();
		logMessage.append("{");

		for (Map.Entry<JMSQueueConnection, QueueSessionPool> tEntry : mConnectionPool
				.entrySet()) {
			logMessage.append(tEntry.getKey().getConnection());
			logMessage.append(":[");

			for (Map.Entry<JMSQueueSession, Boolean> tSessionPool : tEntry
					.getValue().getSessionPool().entrySet()) {
				logMessage.append("{");
				logMessage.append(tSessionPool.getKey().getSession());
				logMessage.append(":");
				logMessage.append(tSessionPool.getValue());
				logMessage.append("},");
			}

			logMessage.append("]");
		}
		logMessage.append("}");

		Logger.info("Current resources of connections & session in the pool are:::: "
				+ logMessage);
	}
}
