package com.jmsproject.jms.pooling.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.pooling.JMSMessageConfiguration;
import com.jmsproject.jms.pooling.QueueConstants;

/**
 * This factory class creates and holds the connection factory object of type
 * {@link ConnectionFactory} and {@link Connection}. It first look up the
 * {@link ConnectionFactory} object & queue object of type {@link Destination}
 * using JNDI, if it is configured, else create a new object. It also maintains
 * other configuration parameters required and defined for JMS queue.
 * 
 * @author Ashish Tripathi
 * 
 */
public class JMSMessageConnectionFactory {

	private static final Logger Logger = LoggerFactory
			.getLogger(JMSMessageConnectionFactory.class);

	private ConnectionFactory mFactory;
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

	private Context mJndiContext;

	private boolean mJndiContainerEnabled;
	private String mInitialContextFactory;
	private String mJndiConnectionFactoryNames;

	private boolean mJmsMultithreadingEnabled;
	private boolean mJmsConnectionPoolingEnabled;
	private int mMaxSessionsPerConnection;
	private int mMaxQueueExpiryTime;

	/**
	 * Simple abstraction for supporting multiple JMS providers - only need to
	 * know what method is used for setting JMS url. Holds provider class &
	 * broker url method
	 * 
	 */
	private static ConnectionFactoryDefinition mSupportedConnectionFactory = null;

	public JMSMessageConnectionFactory() {
	}

	public JMSMessageConnectionFactory(JMSMessageConfiguration pJMSConfiguration) {

		setJMSConnectionParameters(pJMSConfiguration);
	}

	/**
	 * Configure and set all the Queue configuration parameters.
	 * 
	 */
	public void setJMSConnectionParameters(
			JMSMessageConfiguration pJMSConfiguration) {
		mServerUrl = pJMSConfiguration.getServerUrl();
		mUserName = pJMSConfiguration.getUserName();
		mUserPassword = pJMSConfiguration.getUserPassword();
		mQueueName = pJMSConfiguration.getQueueName();
		mConnectionFactoryClass = pJMSConfiguration.getConnectionFactoryClass();
		mBrokerURLMethod = pJMSConfiguration.getBrokerURLMethod();
		mQueueProviderEnabled = pJMSConfiguration.isQueueProviderEnabled();

		mConnAttemptTimeout = pJMSConfiguration.getConnAttemptTimeout();
		mConnAttemptCount = pJMSConfiguration.getConnAttemptCount();
		mConnAttemptDelay = pJMSConfiguration.getConnAttemptDelay();
		mReconnAttemptTimeout = pJMSConfiguration.getReconnAttemptTimeout();
		mReconnAttemptCount = pJMSConfiguration.getReconnAttemptCount();
		mReconnAttemptDelay = pJMSConfiguration.getReconnAttemptDelay();

		mJndiContainerEnabled = pJMSConfiguration.isJndiContainerEnabled();
		mInitialContextFactory = pJMSConfiguration.getInitialContextFactory();
		mJndiConnectionFactoryNames = pJMSConfiguration
				.getJndiConnectionFactoryNames();

		mJmsMultithreadingEnabled = pJMSConfiguration
				.isJmsMultithreadingEnabled();
		mJmsConnectionPoolingEnabled = pJMSConfiguration
				.isJmsConnectionPoolingEnabled();
		mMaxSessionsPerConnection = pJMSConfiguration
				.getMaxSessionsPerConnection();
		mMaxQueueExpiryTime = pJMSConfiguration.getMaxQueueExpiryTime();

		mSupportedConnectionFactory = new ConnectionFactoryDefinition(
				mConnectionFactoryClass, mBrokerURLMethod);
	}

	/**
	 * Create an instance of ConnectionFactory using the queue configuration
	 * parameters.
	 * 
	 * @return {@link ConnectionFactory} instance
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public ConnectionFactory createInstance() throws NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		if (mFactory == null) {
			// mFactory = createConnectionFactory();

			mFactory = createConnectionFactoryUsingJNDI();

			/*
			 * if (mFactory != null && mFactory instanceof
			 * TibjmsConnectionFactory) { // set the timeout (in miliseconds)
			 * configuration for TIBCO // queue
			 * ((TibjmsConnectionFactory)mFactory
			 * ).setConnAttemptTimeout(mConnAttemptTimeout);
			 * ((TibjmsConnectionFactory
			 * )mFactory).setConnAttemptCount(mConnAttemptCount);
			 * ((TibjmsConnectionFactory
			 * )mFactory).setConnAttemptDelay(mConnAttemptDelay);
			 * 
			 * ((TibjmsConnectionFactory)mFactory).setReconnAttemptTimeout(
			 * mReconnAttemptTimeout);
			 * ((TibjmsConnectionFactory)mFactory).setReconnAttemptCount
			 * (mReconnAttemptCount);
			 * ((TibjmsConnectionFactory)mFactory).setReconnAttemptDelay
			 * (mReconnAttemptDelay); }
			 */
		}

		return mFactory;
	}

	/**
	 * Support class to ease addition of new JMS providers to hold provider
	 * class and broker URL method. Can be used as an array in case of multiple
	 * providers.
	 * 
	 */
	private static class ConnectionFactoryDefinition {
		public String className;
		public String serverUrlSetterMethod;

		public ConnectionFactoryDefinition(String pClassName,
				String pServerUrlSetterMethod) {
			this.className = pClassName;
			this.serverUrlSetterMethod = pServerUrlSetterMethod;
		}
	}

	/**
	 * Create instance of connection factory for the given provider class.
	 * Validate the broker url method within that provider class.
	 * 
	 * @return {@link ConnectionFactory}
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ConnectionFactory createConnectionFactory()
			throws NoSuchMethodException, SecurityException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Object obj = null;
		if (mSupportedConnectionFactory.className
				.equals(mConnectionFactoryClass)) {
			Class clazz = Class.forName(mConnectionFactoryClass);
			obj = clazz.newInstance();

			Method serverUrlSetter = clazz.getMethod(
					mSupportedConnectionFactory.serverUrlSetterMethod,
					String.class);
			serverUrlSetter.invoke(obj, mServerUrl);
		}

		return (ConnectionFactory) obj;
	}

	/**
	 * Create instance of connection factory using JNDI context. Validate the
	 * broker url method within that provider class.
	 * 
	 * @return {@link ConnectionFactory}
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	private ConnectionFactory createConnectionFactoryUsingJNDI()
			throws NoSuchMethodException, SecurityException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		ConnectionFactory tConnectionFactory = null;

		// lookup for connection factory instance using JNDI first.
		if (mJndiContainerEnabled && mInitialContextFactory != null
				&& mJndiConnectionFactoryNames != null) {
			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					mInitialContextFactory);
			props.setProperty(Context.PROVIDER_URL, mServerUrl);
			props.setProperty(
					QueueConstants.PARAM_PREFIX.concat(QueueConstants.DOT)
							.concat("AusPostCOM-PCC")
							.concat(QueueConstants.DOT)
							.concat(QueueConstants.PARAM_QUEUE_NAME),
					mQueueName);

			try {
				mJndiContext = new InitialContext(props);
				tConnectionFactory = (ConnectionFactory) mJndiContext
						.lookup(mJndiConnectionFactoryNames);
			} catch (Exception e) {
				Logger.error(
						"Instance for queue connection factory could not be found using JNDI lookup for InitialContextFactory: "
								.concat(mInitialContextFactory)
								.concat(" & ConnectionFactoryNames: ")
								.concat(mJndiConnectionFactoryNames),
						e.getMessage(), e);
			}
		} else {
			Logger.warn("Either JNDI container is disabled of JNDI configuration parameters could not be retrieved. InitialContextFactory: "
					.concat(mInitialContextFactory)
					.concat("  & ConnectionFactoryNames: ")
					.concat(mJndiConnectionFactoryNames)
					.concat(" & value of JndiContainerEnabled flag: ")
					+ mJndiContainerEnabled);
		}

		// create in the instance of queue connection factory if it couldn't be
		// found in JNDI lookup.
		if (tConnectionFactory == null) {
			tConnectionFactory = createConnectionFactory();
		} else {
			Method serverUrlSetter = tConnectionFactory.getClass().getMethod(
					mBrokerURLMethod, String.class);
			serverUrlSetter.invoke(tConnectionFactory, mServerUrl);
		}

		return tConnectionFactory;
	}

	/**
	 * Creates a JMS connection for user/password, if given. It should not be
	 * defined as static class level variable as this connection instance can
	 * not be shared. Each PUSH request will have its own Connection object.
	 * 
	 * @return JMS Connection object of type {@link Connection}
	 * @throws JMSException
	 */
	public Connection getConnection() throws JMSException {
		return mFactory.createConnection(mUserName, mUserPassword);
	}

	/**
	 * @return mQueueName defined in domain specific configuration
	 */
	public String getQueueName() {
		return mQueueName;
	}

	/**
	 * @return the mQueueProviderEnabled
	 */
	public boolean isQueueProviderEnabled() {
		return mQueueProviderEnabled;
	}

	/**
	 * returns the ConnectionFactory instance
	 * 
	 * @return mFactory of type {@link ConnectionFactory}
	 */
	public ConnectionFactory getFactory() {
		return mFactory;
	}

	/**
	 * Returns the current JNDI initial context
	 * 
	 * @return mJndiContext of type {@link Context}
	 */
	public Context getJndiContext() {
		return mJndiContext;
	}

	/**
	 * @return the queue of type {@link Destination} if JNDI is configured, else
	 *         returns NULL.
	 */
	public Destination getQueueViaJNDI() {
		if (mJndiContext == null) {
			return null;
		}

		try {
			return (Destination) mJndiContext
					.lookup("AusPostCOM-PCC".concat(QueueConstants.DOT).concat(
							QueueConstants.PARAM_QUEUE_NAME));
		} catch (NamingException e) {
			Logger.error(
					"Exception ocurred while getting the Queue via JNDI for the queue name: "
							.concat("AusPostCOM-PCC".concat(QueueConstants.DOT)
									.concat(QueueConstants.PARAM_QUEUE_NAME)),
					e.getMessage(), e);
		}

		return null;
	}

	/**
	 * @return True if multithreading is enabled, else False
	 */
	public boolean isJmsMultithreadingEnabled() {
		return mJmsMultithreadingEnabled;
	}

	/**
	 * @return True if connection pooling is enabled, else False
	 */
	public boolean isJmsConnectionPoolingEnabled() {
		return mJmsConnectionPoolingEnabled;
	}

	public int getMaxSessionsPerConnection() {
		return mMaxSessionsPerConnection;
	}

	public int getMaxQueueExpiryTime() {
		return mMaxQueueExpiryTime;
	}

}
