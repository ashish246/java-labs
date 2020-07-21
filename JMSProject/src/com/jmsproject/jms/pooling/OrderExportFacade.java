package com.jmsproject.jms.pooling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.executor.ExecutorServiceHelper;
import com.jmsproject.jms.pooling.factory.JMSMessageConnectionFactory;
import com.jmsproject.jms.pooling.pool.QueuePoolMgr;

/**
 * This facade class encapsulate the complex logic of generating XML, creating
 * JMS connections/sessions and managing multithreading and pooling, if enabled
 * and sending the order XML to the queue.
 * 
 * @author Ashish Tripathi
 * 
 */
public class OrderExportFacade {
	private static final Logger Logger = LoggerFactory
			.getLogger(OrderExportFacade.class);

	public OrderExportFacade() {
	}

	public static void main(String[] args) {

		List<String> tMessageList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			tMessageList.add("Sample String to test JMS Pooling framework: "
					+ i);
		}

		// send all these messages to queue
		processOrderExport(tMessageList);
	}

	/**
	 * Generate the order export XML for each order in iterator and process that
	 * XML to the JMS queue. Loads all the JMS queue configurations and clean up
	 * the connection/session pool if pooling is enabled.
	 * 
	 * @param pOrderIter
	 * @param pChannelDomain
	 */
	public static void processOrderExport(List<String> tMessageList) {

		Iterator<String> tIter = tMessageList.iterator();
		Properties props = new Properties();
		try {
			props.load(ClassLoader
					.getSystemResourceAsStream("development_jmsqueue.properties"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		JMSMessageConfiguration tJMSConfigs = new JMSMessageConfiguration(props);

		if (tJMSConfigs.isQueueProviderEnabled()) {
			// if pooling is enabled, clean up older connections & sessions in
			// the pool, before the export starts
			if (tJMSConfigs.isJmsConnectionPoolingEnabled()) {
				Logger.info(
						"Cleaning up JMS connection & sessions from the pool which are older than '"
								.concat(Integer.valueOf(
										tJMSConfigs.getMaxQueueExpiryTime())
										.toString()).concat(
										"' minutes for the domain: {}"),
						new Object[] { "AusPostCOM-PCC" });

				QueuePoolMgr.cleanUp(tJMSConfigs.getMaxQueueExpiryTime());
			}

			// create instance of connection factory
			JMSMessageConnectionFactory tJMSMessageConnFactory = getJMSConnectionFactory(tJMSConfigs);

			Logger.info(
					"Value of the flag for JMS connection & session pooling is '"
							.concat(Boolean
									.valueOf(
											tJMSConfigs
													.isJmsConnectionPoolingEnabled())
									.toString()).concat("' for the domain: {}"),
					new Object[] { "AusPostCOM-PCC" });

			// If multithreading is enabled
			if (tJMSConfigs.isJmsMultithreadingEnabled()) {
				Logger.info("Multithreading is enabled for the domain with configurations (maxParallelThreads, maxServiceTimeout, maxPerThreadTimeout): "
						.concat("AusPostCOM-PCC")
						.concat(" (")
						.concat(Integer.valueOf(
								tJMSConfigs.getMaxParralelThreads()).toString())
						.concat(", ")
						.concat(Integer.valueOf(
								tJMSConfigs.getMaxExecutorServiceTimeout())
								.toString())
						.concat(", ")
						.concat(Integer.valueOf(
								tJMSConfigs.getMaxPerThreadTimeout())
								.toString()).concat(")."));

				// create a executor service
				ExecutorServiceHelper tExecutorServiceHelper = new ExecutorServiceHelper(
						tJMSConfigs.getMaxParralelThreads(),
						tJMSConfigs.getMaxExecutorServiceTimeout(),
						tJMSConfigs.getMaxParralelThreads());

				ExecutorService tExecutorService = tExecutorServiceHelper
						.getExecutorService();

				while (tIter.hasNext()) {

					// create the factory instance if it already doesn't exist.
					if (tJMSMessageConnFactory == null) {
						tJMSMessageConnFactory = getJMSConnectionFactory(tJMSConfigs);
					}

					if (tJMSMessageConnFactory == null) {
						// skip the execution, if factory is still NULL.
						Logger.warn("Intance of a JMS connection factory could not be found for the order number: {}");
						continue;
					}

					// generate the order export XML
					String tExportXML = tIter.next();

					if (tExportXML == null) {
						Logger.error(
								"Order export xml could not be generated for the order id: {}",
								new Object[] {});

						continue;
					}

					tExecutorService.execute(new ExportOrderToQueue(tExportXML,
							tJMSMessageConnFactory));
				}

				tExecutorServiceHelper.shutdownService(tExecutorService);
			} else {
				Logger.info("Multithreading is disabled for the domain: {}",
						new Object[] { "AusPostCOM-PCC" });

				// single thread processing

				while (tIter.hasNext()) {
					// create the factory instance if it already doesn't exist.
					if (tJMSMessageConnFactory == null) {
						tJMSMessageConnFactory = getJMSConnectionFactory(tJMSConfigs);
					}

					if (tJMSMessageConnFactory == null) {
						Logger.warn(
								"Intance of a JMS connection factory could not be found for the domain & order number: {}",
								new Object[] { "AusPostCOM-PCC" });
						// skip the execution, if factory is still NULL.
						continue;
					}

					// generate the order export XML
					String tExportXML = tIter.next();
					if (tExportXML == null) {
						Logger.error(
								"Order export xml could not be generated for the order id: {}",
								new Object[] {});

						continue;
					}

					new ExportOrderToQueue(tExportXML, tJMSMessageConnFactory)
							.processQueue();
				}
			}

			// log the current resources in the pool
			if (tJMSConfigs.isJmsConnectionPoolingEnabled()) {
				QueuePoolMgr.logResources();
			}
		} else {
			Logger.warn("Queue provider is disabled for the domain: {}",
					new Object[] { "AusPostCOM-PCC" });
		}

	}

	/**
	 * Create an object of connection factory which will be used across the
	 * threads (if multithreading enabled) to create connection objects.
	 * 
	 * @param tJMSConfiguration
	 * @return instance of {@link JMSMessageConnectionFactory}
	 */
	private static JMSMessageConnectionFactory getJMSConnectionFactory(
			JMSMessageConfiguration tJMSConfiguration) {
		JMSMessageConnectionFactory tJMSMessageConnFactory = new JMSMessageConnectionFactory(
				tJMSConfiguration);
		try {
			tJMSMessageConnFactory.createInstance();

			Logger.info("Instance of connection factory of type '"
					+ tJMSMessageConnFactory.getFactory().getClass().getName()
					+ "' was successfully created for the domain: {}",
					new Object[] { "AusPostCOM-PCC" });
		} catch (Exception e) {
			Logger.error(
					"Exception occured while creating instance of connection factory.",
					e.getMessage(), e);

			return null;
		}

		return tJMSMessageConnFactory;
	}

}
