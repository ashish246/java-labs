package com.jmsproject.jms.executor;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutorServiceHelper {

	private static final Logger Logger = LoggerFactory
			.getLogger(ExecutorServiceHelper.class);

	private int mMaxThreads;

	private int mMaxTimeout;

	// it is same as mMaxTimeout as of now.
	private int mMaxTimeoutPerThread;

	public ExecutorServiceHelper(Properties pProperty) {
		Properties props = pProperty;
		/*
		 * props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
		 * "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		 * props.setProperty(Context.PROVIDER_URL,"tcp://hostname:61616");
		 */

		mMaxThreads = Integer
				.valueOf(props.getProperty(
						PCCConstants.MAX_PARALLEL_THREADS_PER_REQUEST,
						String.valueOf(PCCConstants.MAX_PARALLEL_THREADS_PER_REQUEST_DEFAULT)));

		mMaxTimeout = Integer
				.valueOf(props.getProperty(
						PCCConstants.MAX_EXECUTOR_SERVICE_TIMEOUT,
						String.valueOf(PCCConstants.MAX_EXECUTOR_SERVICE_TIMEOUT_DEFAULT)));

		mMaxTimeoutPerThread = Integer
				.valueOf(props.getProperty(
						PCCConstants.MAX_EXECUTOR_SERVICE_TIMEOUT,
						String.valueOf(PCCConstants.MAX_EXECUTOR_SERVICE_TIMEOUT_DEFAULT)));
	}

	public ExecutorServiceHelper(int pMaxThreads, int pMaxTimeout,
			int pMaxTimeoutPerThread) {
		mMaxThreads = pMaxThreads;

		mMaxTimeout = pMaxTimeout;

		mMaxTimeoutPerThread = pMaxTimeoutPerThread;
	}

	/**
	 * Return an instance of executor service with a fixed thread pool of core &
	 * max pool size equal to 'mMaxThreads'. This service can be used for
	 * concurrent processing of tasks.
	 * 
	 * @return {@link ExecutorService}
	 */
	public ExecutorService getExecutorService() {
		return Executors.newFixedThreadPool(mMaxThreads);
	}

	/**
	 * Return an instance of executor service with a fixed thread pool of core &
	 * max pool size equal to 'pMaxThreads'. This service can be used for
	 * concurrent processing of tasks.
	 * 
	 * @param pMaxThreads
	 * @return {@link ExecutorService}
	 */
	public ExecutorService getExecutorService(int pMaxThreads) {
		return Executors.newFixedThreadPool(mMaxThreads);
	}

	public int getMaxThreads() {
		return mMaxThreads;
	}

	public int getMaxTimeout() {
		return mMaxTimeout;
	}

	/**
	 * Shutdown the service 'pExecutorService' once all the task submitted
	 * previously have completed their execution.
	 * 
	 * @param pExecutorService
	 *            of type {@link ExecutorService}
	 */
	public void shutdownService(ExecutorService pExecutorService) {
		pExecutorService.shutdown();

		try {
			pExecutorService.awaitTermination(mMaxTimeout, TimeUnit.SECONDS);
		} catch (InterruptedException iEx) {
			Logger.error(iEx.getMessage(), iEx);
		}
	}

	/**
	 * Shutdown the service 'pExecutorService' once all the task submitted
	 * previously have completed their execution.
	 * 
	 * @param pExecutorService
	 *            of type {@link ExecutorService}
	 * @param pMaxTimeout
	 */
	public void shutdownService(ExecutorService pExecutorService,
			int pMaxTimeout) {
		pExecutorService.shutdown();

		try {
			pExecutorService.awaitTermination(pMaxTimeout, TimeUnit.SECONDS);
		} catch (InterruptedException iEx) {
			Logger.error(iEx.getMessage(), iEx);
		}
	}

	/**
	 * Gets the result from the Future pending tasks, if they are completed
	 * successfully.
	 * 
	 * @param pFuture
	 * @return the object of T type
	 */
	public <T> T getFutureTaskResult(Future<T> pFuture) {
		try {
			return pFuture.get(mMaxTimeoutPerThread, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			Logger.error(e.getMessage(), e);

			return null;
		}
	}

	/**
	 * Gets the result from the Future pending tasks, if they are completed
	 * successfully.
	 * 
	 * @param pMaxTimeoutPerThread
	 * @param pFuture
	 * @return the object of T type
	 */
	public <T> T getFutureTaskResult(Future<T> pFuture, int pMaxTimeoutPerThread) {
		try {
			return pFuture.get(pMaxTimeoutPerThread, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			Logger.error(e.getMessage(), e);

			return null;
		}
	}

}
