package com.jmsproject.jms.pooling;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSMessageConfiguration {
	private static final Logger Logger = LoggerFactory
			.getLogger(JMSMessageConfiguration.class);

	private String mConnectionFactoryClass;
	private String mBrokerURLMethod;
	private String mServerUrl;
	private String mUserName;
	private String mUserPassword;
	private String mQueueName;
	private boolean mQueueProviderEnabled;

	private int mConnAttemptTimeout;
	private int mConnAttemptCount;
	private int mConnAttemptDelay;
	private int mReconnAttemptTimeout;
	private int mReconnAttemptCount;
	private int mReconnAttemptDelay;

	private boolean mJndiContainerEnabled;
	private String mInitialContextFactory;
	private String mJndiConnectionFactoryNames;

	private boolean mJmsMultithreadingEnabled;
	private int mMaxParralelThreads;
	private int mMaxExecutorServiceTimeout;
	private int mMaxPerThreadTimeout;

	private boolean mJmsConnectionPoolingEnabled;
	private int mMaxSessionsPerConnection;
	private int mMaxQueueExpiryTime;

	public JMSMessageConfiguration(Properties props) {
		String tDomainName = "AusPostCOM-PCC";

		// set broker URL.
		mServerUrl = props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_BROKER_URL),
				QueueConstants.PARAM_BROKER_URL_DEFAULT);

		Logger.debug("Server URL for the queue provider is:  {}",
				new Object[] { mServerUrl });

		// Set credential details
		mUserName = props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_USER_NAME),
				QueueConstants.PARAM_USER_NAME_DEFAULT);

		Logger.debug("User name for the queue provider is:  {}",
				new Object[] { mUserName });

		mUserPassword = props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_USER_PASSWORD),
				QueueConstants.PARAM_USER_PASSWORD_DEFAULT);

		// set Queue name
		mQueueName = props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_QUEUE_NAME),
				QueueConstants.PARAM_QUEUE_NAME_DEFAULT);

		Logger.debug("Queue name for the queue provider is:  {}",
				new Object[] { mQueueName });

		// set Queue provider details
		mConnectionFactoryClass = props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_CONNECTION_FACTORY_CLASS),
				QueueConstants.PARAM_CONNECTION_FACTORY_CLASS_DEFAULT);

		Logger.debug("Connection factory class for the queue provider is:  {}",
				new Object[] { mConnectionFactoryClass });

		mBrokerURLMethod = props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_BROKER_URL_METHOD),
				QueueConstants.PARAM_BROKER_URL_METHOD_DEFAULT);

		Logger.debug("Broker URL method for the queue provider is:  {}",
				new Object[] { mBrokerURLMethod });

		mQueueProviderEnabled = Boolean
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_QUEUE_PROVIDER_ENABLED),
								String.valueOf(QueueConstants.PARAM_QUEUE_PROVIDER_ENABLED_DEFAULT)));

		Logger.debug("Value of QueueProviderEnabled flag is :  {}",
				new Object[] { mQueueProviderEnabled });

		// get timeout configuration parameters
		mConnAttemptTimeout = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_TIBCO_CONN_TIMEOUT),
								String.valueOf(QueueConstants.PARAM_TIBCO_CONN_TIMEOUT_DEFAULT)));

		Logger.debug(
				"Connection attempt timeout for the queue connection is:  {}",
				new Object[] { mConnAttemptTimeout });

		mConnAttemptCount = Integer.valueOf(props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_TIBCO_CONN_COUNT),
				String.valueOf(QueueConstants.PARAM_TIBCO_CONN_COUNT_DEFAULT)));

		Logger.debug(
				"Connection attempt count for the queue connection is:  {}",
				new Object[] { mConnAttemptCount });

		mConnAttemptDelay = Integer.valueOf(props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_TIBCO_CONN_DELAY),
				String.valueOf(QueueConstants.PARAM_TIBCO_CONN_DELAY_DEFAULT)));

		Logger.debug(
				"Connection attempt delay for the queue connection is:  {}",
				new Object[] { mConnAttemptDelay });

		mReconnAttemptTimeout = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_TIBCO_RECONN_TIMEOUT),
								String.valueOf(QueueConstants.PARAM_TIBCO_RECONN_TIMEOUT_DEFAULT)));

		Logger.debug(
				"Reconnection attempt timeout for the queue connection is:  {}",
				new Object[] { mReconnAttemptTimeout });

		mReconnAttemptCount = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_TIBCO_RECONN_COUNT),
								String.valueOf(QueueConstants.PARAM_TIBCO_RECONN_COUNT_DEFAULT)));

		Logger.debug(
				"Reconnection attempt count for the queue connection is:  {}",
				new Object[] { mReconnAttemptCount });

		mReconnAttemptDelay = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_TIBCO_RECONN_DELAY),
								String.valueOf(QueueConstants.PARAM_TIBCO_RECONN_DELAY_DEFAULT)));

		Logger.debug(
				"Reconnection attempt delay for the queue connection is:  {}",
				new Object[] { mReconnAttemptDelay });

		// get jndi configuration parameters
		mJndiContainerEnabled = Boolean
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_JNDI_CONTAINER_ENABLED),
								String.valueOf(QueueConstants.PARAM_JNDI_CONTAINER_ENABLED_DEFAULT)));

		Logger.debug("Value of jndiContainerEnabled flag is:  {}",
				new Object[] { mJndiContainerEnabled });

		mInitialContextFactory = props.getProperty(
				QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
						.concat(tDomainName).concat(QueueConstants.DOT)
						.concat(QueueConstants.PARAM_JNDI_INITIAL_FACTORY),
				QueueConstants.PARAM_JNDI_INITIAL_FACTORY_DEFAULT);

		Logger.debug(
				"Initial Context factory for the queue connection is:  {}",
				new Object[] { mInitialContextFactory });

		mJndiConnectionFactoryNames = props
				.getProperty(
						QueueConstants.PARAM_PREFIX
								.concat(QueueConstants.DOT)
								.concat(tDomainName)
								.concat(QueueConstants.DOT)
								.concat(QueueConstants.PARAM_JNDI_CONTEXT_FACTORY_NAMES),
						QueueConstants.PARAM_JNDI_CONTEXT_FACTORY_NAMES_DEFAULT);

		Logger.debug(
				"Connection factory names for the queue connection is:  {}",
				new Object[] { mJndiConnectionFactoryNames });

		// JMS multithreading configurations
		mJmsMultithreadingEnabled = Boolean
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_MULTITHREADING_ENABLED),
								String.valueOf(QueueConstants.PARAM_MULTITHREADING_ENABLED_DEFAULT)));

		Logger.debug("Value of jmsMultithreadingEnabled flag is:  {}",
				new Object[] { mJmsMultithreadingEnabled });

		mMaxParralelThreads = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_MAX_PARALLEL_THREADS),
								String.valueOf(QueueConstants.PARAM_MAX_PARALLEL_THREADS_DEFAULT)));

		Logger.debug("Value of maxParralelThreads flag is:  {}",
				new Object[] { mMaxParralelThreads });

		mMaxExecutorServiceTimeout = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_MAX_EXECUTOR_SERVICE_TIMEOUT),
								String.valueOf(QueueConstants.PARAM_MAX_EXECUTOR_SERVICE_TIMEOUT_DEFAULT)));

		Logger.debug("Value of maxExecutorServiceTimeout flag is:  {}",
				new Object[] { mMaxExecutorServiceTimeout });

		mMaxPerThreadTimeout = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_MAX_PER_THREAD_TIMEOUT),
								String.valueOf(QueueConstants.PARAM_MAX_PER_THREAD_TIMEOUT_DEFAULT)));

		Logger.debug("Value of maxPerThreadTimeout flag is:  {}",
				new Object[] { mMaxPerThreadTimeout });

		// JMS pooling configurations
		mJmsConnectionPoolingEnabled = Boolean
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_CONNECTION_POOLING_ENABLED),
								String.valueOf(QueueConstants.PARAM_CONNECTION_POOLING_ENABLED_DEFAULT)));

		Logger.debug("Value of jmsConnectionPoolingEnabled flag is:  {}",
				new Object[] { mJmsConnectionPoolingEnabled });

		mMaxSessionsPerConnection = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_MAX_SESSIONS_PER_CONNECTION),
								String.valueOf(QueueConstants.PARAM_MAX_SESSIONS_PER_CONNECTION_DEFAULT)));

		Logger.debug("Value of maxSessionsPerConnection flag is:  {}",
				new Object[] { mMaxSessionsPerConnection });

		mMaxQueueExpiryTime = Integer
				.valueOf(props
						.getProperty(
								QueueConstants.PARAM_PREFIX
										.concat(QueueConstants.DOT)
										.concat(tDomainName)
										.concat(QueueConstants.DOT)
										.concat(QueueConstants.PARAM_MAX_QUEUE_EXPIRY_TIME),
								String.valueOf(QueueConstants.PARAM_MAX_QUEUE_EXPIRY_TIME_DEFAULT)));

		Logger.debug("Value of maxQueueExpiryTime flag is:  {}",
				new Object[] { mMaxQueueExpiryTime });

	}

	public String getConnectionFactoryClass() {
		return mConnectionFactoryClass;
	}

	public String getBrokerURLMethod() {
		return mBrokerURLMethod;
	}

	public String getServerUrl() {
		return mServerUrl;
	}

	public String getUserName() {
		return mUserName;
	}

	public String getUserPassword() {
		return mUserPassword;
	}

	public String getQueueName() {
		return mQueueName;
	}

	/**
	 * @return the mQueueProviderEnabled
	 */
	public boolean isQueueProviderEnabled() {
		return mQueueProviderEnabled;
	}

	public int getConnAttemptTimeout() {
		return mConnAttemptTimeout;
	}

	public int getConnAttemptCount() {
		return mConnAttemptCount;
	}

	public int getConnAttemptDelay() {
		return mConnAttemptDelay;
	}

	public int getReconnAttemptTimeout() {
		return mReconnAttemptTimeout;
	}

	public int getReconnAttemptCount() {
		return mReconnAttemptCount;
	}

	public int getReconnAttemptDelay() {
		return mReconnAttemptDelay;
	}

	public String getInitialContextFactory() {
		return mInitialContextFactory;
	}

	public String getJndiConnectionFactoryNames() {
		return mJndiConnectionFactoryNames;
	}

	public boolean isJndiContainerEnabled() {
		return mJndiContainerEnabled;
	}

	public boolean isJmsMultithreadingEnabled() {
		return mJmsMultithreadingEnabled;
	}

	public boolean isJmsConnectionPoolingEnabled() {
		return mJmsConnectionPoolingEnabled;
	}

	public int getMaxParralelThreads() {
		return mMaxParralelThreads;
	}

	public int getMaxExecutorServiceTimeout() {
		return mMaxExecutorServiceTimeout;
	}

	public int getMaxPerThreadTimeout() {
		return mMaxPerThreadTimeout;
	}

	public int getMaxSessionsPerConnection() {
		return mMaxSessionsPerConnection;
	}

	public int getMaxQueueExpiryTime() {
		return mMaxQueueExpiryTime;
	}
}
