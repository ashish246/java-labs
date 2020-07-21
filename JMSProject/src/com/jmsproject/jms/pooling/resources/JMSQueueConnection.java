package com.jmsproject.jms.pooling.resources;

import java.util.Calendar;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.JMSException;

/**
 * Holds a {@link Connection} object and its creation date and few other
 * information related to this object.
 * 
 * @author Ashish Tripathi
 * 
 */
public class JMSQueueConnection
{
    private Connection mConnection;

    private Date mCreationTime;

    private boolean isCorrupted = false;

    private boolean isSessionInUse = false;

    public JMSQueueConnection(Connection pConnection)
    {
        mConnection = pConnection;
        mCreationTime = new Date();
    }

    /**
     * @return the {@link Connection} object held by this class.
     */
    public Connection getConnection()
    {
        return mConnection;
    }

    public Date getCreationTime()
    {
        return mCreationTime;
    }

    public void start() throws JMSException
    {
        if (mConnection != null)
        {
            mConnection.start();
        }
    }

    /**
     * Closes the {@link Connection} this class holds.
     * 
     * @throws JMSException
     */
    public void close() throws JMSException
    {
        if (mConnection != null)
        {
            mConnection.close();
            mConnection = null;
        }
    }

    /**
     * @param pMinutes
     * @return true, if creation date of {@link Connection} object held by
     *         this class is older by 'pMinutes' from current date time, else
     *         false.
     */
    public boolean isOlderThan(int pMinutes)
    {
        Calendar tCal = Calendar.getInstance();
        tCal.add(Calendar.MINUTE, -pMinutes);
        Date tPrevExpiryDate = tCal.getTime();

        return mCreationTime.before(tPrevExpiryDate);
    }

    public boolean isCorrupted()
    {
        return isCorrupted;
    }

    public void setCorrupted(boolean pIsCorrupted)
    {
        isCorrupted = pIsCorrupted;
    }

    public boolean isSessionInUse()
    {
        return isSessionInUse;
    }

    public void setSessionInUse(boolean pIsSessionInUse)
    {
        isSessionInUse = pIsSessionInUse;
    }

}
