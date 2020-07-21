package com.jmsproject.jms.pooling;

import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.pooling.factory.JMSMessageConnectionFactory;
import com.jmsproject.jms.pooling.factory.QueueConnectionMgr;
import com.jmsproject.jms.pooling.provider.JMSQueueProvider;

/**
 * Helper class to process order export to JMS queue for a single order using a
 * single {@link Session}.
 * 
 * @author Ashish Tripathi
 * 
 */
public class ExportOrderToQueue implements Runnable {

	private static final Logger Logger = LoggerFactory
			.getLogger(ExportOrderToQueue.class);

	private String mExportXML;

	private JMSMessageConnectionFactory mJMSMessageConnFactory;

	private boolean isPushSuccessful = false;

	public ExportOrderToQueue(String pExportXML,
			JMSMessageConnectionFactory pJMSMessageConnFactory) {
		mExportXML = pExportXML;
		mJMSMessageConnFactory = pJMSMessageConnFactory;
	}

	@Override
	public void run() {
		processQueue();
	}

	/**
	 * Push the generated order export XML into the queue for one order using
	 * one single {@link Session}. Uses {@link QueueConnectionMgr} to manage JMS
	 * connections and session resources. Updates the order status if export is
	 * successful.
	 * 
	 * @param pOrder
	 * @param pChannelDomain
	 * 
	 * @return true, if the process is completed successfully, else false.
	 */
	public boolean processQueue() {
		QueueConnectionMgr tQueueConnMgr = new QueueConnectionMgr(
				mJMSMessageConnFactory, "AusPostCOM-PCC");

		// get active & available queue session for current order processing
		Session tQueueSession = tQueueConnMgr.getAvailableQueueSession();

		if (tQueueSession == null) {
			Logger.error(
					"Session object could not be found for the order id: {}",
					new Object[] {});

			return isPushSuccessful;
		}

		JMSQueueProvider tJMSProvider = new JMSQueueProvider(
				mJMSMessageConnFactory.getQueueViaJNDI(),
				mJMSMessageConnFactory.getQueueName());

		try {
			// start the JMS session
			tJMSProvider.startSession(tQueueSession);

			Logger.info(
					"Producer for the queue session is successfully created for the order id: {}",
					new Object[] {});

			// send the message to queue
			try {
				Logger.info(
						"Attempting to push the order export xml to the queue for the order id: {}",
						new Object[] {});

				tJMSProvider.sendMessage(mExportXML);

				isPushSuccessful = true;

				Logger.info(
						"Order export xml is successefully pushed to the queue for the order id: {}",
						new Object[] {});
			} catch (Exception e1) {
				Logger.error(
						"Order export xml could not be pushed to the queue for the order id: ",
						e1.getMessage(), e1);
			}
		} catch (Exception e2) {
			Logger.error(
					"Exception occured while setting up JMS session to the queue for the order id: ",
					e2.getMessage(), e2);
		}

		// close the producers created by this session
		try {
			tJMSProvider.closeProducer();
		} catch (Exception e) {
			Logger.error(
					"Exception occured while releasing session & produces for the order id: ",
					e.getMessage(), e);
		} finally {

			Logger.info(
					"Releasing resources into the pool for the order id: {}",
					new Object[] {});

			// release the lock on current queue session
			tQueueConnMgr.releaseQueueResources();
		}

		if (isPushSuccessful) {
			// do something useful
		} else {
			Logger.error(
					"Sending export xml to queue failed for the order with order id : {}",
					new Object[] {});
		}

		return isPushSuccessful;
	}

}