package com.jmsproject.jms.pooling.resources;

import java.util.Calendar;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Holds a {@link Session} object with its creation date time.
 * 
 * @author Ashish Tripathi
 * 
 */
public class JMSQueueSession {
	private Session mSession;

	private Date mCreationTime;

	public JMSQueueSession(Session pSession) {
		mSession = pSession;
		mCreationTime = new Date();
	}

	/**
	 * @return the {@link Session} object held by this class.
	 */
	public Session getSession() {
		return mSession;
	}

	public Date getCreationTime() {
		return mCreationTime;
	}

	/**
	 * Closes the {@link Session} this class holds.
	 * 
	 * @throws JMSException
	 */
	public void close() throws JMSException {
		if (mSession != null) {
			mSession.close();
			mSession = null;
		}
	}

	/**
	 * @param pMinutes
	 * @return true, if creation date of {@link Session} object held by this
	 *         class is older by 'pMinutes' from current date time, else false.
	 */
	public boolean isOlderThan(int pMinutes) {
		Calendar tCal = Calendar.getInstance();
		tCal.add(Calendar.MINUTE, -pMinutes);
		Date tPrevExpiryDate = tCal.getTime();

		return mCreationTime.before(tPrevExpiryDate);
	}
}
