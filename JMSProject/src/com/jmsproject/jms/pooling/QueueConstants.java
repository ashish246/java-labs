package com.jmsproject.jms.pooling;

public class QueueConstants
{

    // define queue paratemer config name
    public static final String DOT = ".";
    public static final String PARAM_PREFIX = "queue";
    public static final String PARAM_BROKER_URL = "brokerURL";
    public static final String PARAM_QUEUE_NAME = "queueName";
    public static final String PARAM_USER_NAME = "userName";
    public static final String PARAM_USER_PASSWORD = "userPassword";
    public static final String PARAM_CONNECTION_FACTORY_CLASS = "connectionFactoryClass";
    public static final String PARAM_BROKER_URL_METHOD = "brokerURLMethod";
    public static final String PARAM_QUEUE_PROVIDER_ENABLED = "queueProviderEnabled";

    // timeout parameters
    public static final String PARAM_TIBCO_CONN_TIMEOUT = "connAttemptTimeout";
    public static final String PARAM_TIBCO_CONN_COUNT = "connAttemptCount";
    public static final String PARAM_TIBCO_CONN_DELAY = "connAttemptDelay";
    public static final String PARAM_TIBCO_RECONN_TIMEOUT = "reconnAttemptTimeout";
    public static final String PARAM_TIBCO_RECONN_COUNT = "reconnAttemptCount";
    public static final String PARAM_TIBCO_RECONN_DELAY = "reconnAttemptDelay";

    // jndi parameters
    public static final String PARAM_JNDI_CONTAINER_ENABLED = "jndiContainerEnabled";
    public static final String PARAM_JNDI_INITIAL_FACTORY = "initialContextFactory";
    public static final String PARAM_JNDI_CONTEXT_FACTORY_NAMES = "connectionFactoryNames";

    // default values of the config params
    public static final String PARAM_BROKER_URL_DEFAULT = "tcp://localhost:61616";
    public static final String PARAM_QUEUE_NAME_DEFAULT = "intershop.com.submitmanifest.LOCAL";
    public static final String PARAM_USER_NAME_DEFAULT = "system";
    public static final String PARAM_USER_PASSWORD_DEFAULT = "manager";
    public static final String PARAM_CONNECTION_FACTORY_CLASS_DEFAULT = "org.apache.activemq.ActiveMQConnectionFactory";
    public static final String PARAM_BROKER_URL_METHOD_DEFAULT = "setBrokerURL";
    public static final boolean PARAM_QUEUE_PROVIDER_ENABLED_DEFAULT = false;

    // timeout is in miliseconds
    public static final int PARAM_TIBCO_CONN_TIMEOUT_DEFAULT = 60000;
    public static final int PARAM_TIBCO_CONN_COUNT_DEFAULT = 3;
    public static final int PARAM_TIBCO_CONN_DELAY_DEFAULT = 5000;
    public static final int PARAM_TIBCO_RECONN_TIMEOUT_DEFAULT = 60000;
    public static final int PARAM_TIBCO_RECONN_COUNT_DEFAULT = 3;
    public static final int PARAM_TIBCO_RECONN_DELAY_DEFAULT = 5000;

    // jndi parameters default values
    public static final boolean PARAM_JNDI_CONTAINER_ENABLED_DEFAULT = false;
    public static final String PARAM_JNDI_INITIAL_FACTORY_DEFAULT = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    public static final String PARAM_JNDI_CONTEXT_FACTORY_NAMES_DEFAULT = "ConnectionFactory";

    // pooling parameters
    public static final String PARAM_CONNECTION_POOLING_ENABLED = "jmsConnectionPoolingEnabled";
    public static final boolean PARAM_CONNECTION_POOLING_ENABLED_DEFAULT = false;
    public static final String PARAM_MAX_SESSIONS_PER_CONNECTION = "maxSessionsPerConnection";
    public static final int PARAM_MAX_SESSIONS_PER_CONNECTION_DEFAULT = 10;
    public static final String PARAM_MAX_QUEUE_EXPIRY_TIME = "maxQueueExpiryTime";
    public static final int PARAM_MAX_QUEUE_EXPIRY_TIME_DEFAULT = 120;// in
                                                                      // minutes

    // Multi-threading parameters
    public static final String PARAM_MULTITHREADING_ENABLED = "jmsMultithreadingEnabled";
    public static final boolean PARAM_MULTITHREADING_ENABLED_DEFAULT = false;
    public static final String PARAM_MAX_PARALLEL_THREADS = "maxParallelThreadsPerExportRequest";
    public static final int PARAM_MAX_PARALLEL_THREADS_DEFAULT = 10;
    public static final String PARAM_MAX_EXECUTOR_SERVICE_TIMEOUT = "maxExecutorServiceTimeoutForExport";
    public static final int PARAM_MAX_EXECUTOR_SERVICE_TIMEOUT_DEFAULT = 600;
    public static final String PARAM_MAX_PER_THREAD_TIMEOUT = "maxPerThreadTimeoutForExport";
    public static final int PARAM_MAX_PER_THREAD_TIMEOUT_DEFAULT = 600;
}
