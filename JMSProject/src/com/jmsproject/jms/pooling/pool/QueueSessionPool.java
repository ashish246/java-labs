package com.jmsproject.jms.pooling.pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsproject.jms.pooling.resources.JMSQueueSession;

/**
 * This class will maintain a pool of {@link JMSQueueSession} instances, each of
 * which will hold a {@link Session} object.
 * 
 * @author Ashish Tripathi
 * 
 */
public class QueueSessionPool
{
	private static final Logger Logger = LoggerFactory
			.getLogger(QueueSessionPool.class);
    /**
     * Map of {@link JMSQueueSession} and isAvailable flag. Flag (isAvailable)
     * is set to False when retrieved for processing and set to True when
     * released.
     */
    private ConcurrentHashMap<JMSQueueSession, Boolean> mSessionPool;

    private int mTotalSessionCount;

    public QueueSessionPool()
    {
        if (mSessionPool == null)
        {
            mSessionPool = new ConcurrentHashMap<>(8, 0.9f, 1);
        }
    }

    /**
     * It returns the queue session object from the session pool which is not
     * locked or being used by any other thread. Before returning, it locks the
     * session object in the session pool so that another thread can not use it
     * until current thread releases it back into the pool.
     * 
     * @return the instance of {@link JMSQueueSession} which holds the
     *         {@link Session}.
     */
    public JMSQueueSession getAvailableSession()
    {
        for (Map.Entry<JMSQueueSession, Boolean> tEntry : mSessionPool.entrySet())
        {
            if (tEntry.getValue() != null && tEntry.getValue())
            {
                tEntry.setValue(false);

                return tEntry.getKey();
            }
        }

        return null;
    }

    /**
     * Adds an instance of {@link JMSQueueSession}, which holds the
     * {@link Session} object, into the session pool and lock it so that another
     * thread can not use it until current thread releases it back into the
     * pool. Also increment the count of total number of sessions in the pool.
     * 
     * @param pJMSQueueSession
     */
    public void addSession(JMSQueueSession pJMSQueueSession)
    {
        mSessionPool.put(pJMSQueueSession, false);

        ++mTotalSessionCount;

        Logger.debug("Resource is successfully added into the pool for the session: {}",
                        new Object[] { pJMSQueueSession });
    }

    /**
     * Removes the given {@link JMSQueueSession} object from the session pool
     * and close the {@link Session} object it holds. Also, decrement the count
     * of total number of session in the pool.
     * 
     * @param pJMSQueueSession
     */
    public void removeSession(JMSQueueSession pJMSQueueSession)
    {
        try
        {
            pJMSQueueSession.close();
        }
        catch(JMSException e)
        {
            Logger.error("Exception occured while closing JMS queue Session.", e.getMessage(),
                            e);
        }

        mSessionPool.remove(pJMSQueueSession);

        --mTotalSessionCount;

        Logger.debug("Resource is successfully removed from the pool for the session: {}",
                        new Object[] { pJMSQueueSession });
    }

    /**
     * Removes all the {@link JMSQueueSession} objects in the session pool and
     * close all the {@link Session} object they hold. Set the counter of total
     * number of sessions in the pool to zero.
     * 
     */
    public void destroy()
    {
        for (Map.Entry<JMSQueueSession, Boolean> tEntry : mSessionPool.entrySet())
        {
            removeSession(tEntry.getKey());
        }

        mSessionPool = null;

        mTotalSessionCount = 0;
    }

    /**
     * Releases the given {@link JMSQueueSession} back in the session pool.
     * 
     * @param tJMSQueueSession
     */
    public void releaseSession(JMSQueueSession tJMSQueueSession)
    {
        mSessionPool.put(tJMSQueueSession, true);

        Logger.debug("Resource is successfully released into the pool for the session: {}",
                        new Object[] { tJMSQueueSession });
    }

    /**
     * @return true, if there is any single session is in released state in the
     *         pool, else returns false.
     */
    public boolean hasJMSSessionAvailable()
    {
        for (Map.Entry<JMSQueueSession, Boolean> tEntry : mSessionPool.entrySet())
        {
            if (tEntry.getValue())
            {
                return true;
            }
        }

        return false;
    }

    /**
     * @param pJMSQueueSession
     * @return true, if the given {@link JMSQueueSession} exists in the session
     *         pool, else false.
     */
    public boolean hasJMSSession(JMSQueueSession pJMSQueueSession)
    {
        for (Map.Entry<JMSQueueSession, Boolean> tEntry : mSessionPool.entrySet())
        {
            if (tEntry.getKey().equals(pJMSQueueSession))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * @return true, if there is any single session is in locked state in the
     *         pool, else returns false.
     */
    public boolean hasJMSSessionLocked()
    {
        for (Map.Entry<JMSQueueSession, Boolean> tEntry : mSessionPool.entrySet())
        {
            if (!tEntry.getValue())
            {
                return true;
            }
        }

        return false;
    }

    /**
     * @return the total number of sessions in the pool.
     */
    public int getTotalSessionCount()
    {
        return mTotalSessionCount;
    }

    /**
     * Removes all the {@link JMSQueueSession} object from the session pool and
     * close the {@link Session} object it holds, if their creation date is
     * older by 'pSessionExpiryTime' from current date. Also, decrement the
     * count of total number of session in the pool for all the sessions
     * removed.
     * 
     * @param pSessionExpiryTime
     *            , in minutes
     */
    public void cleanUp(int pSessionExpiryTime)
    {
        for (Map.Entry<JMSQueueSession, Boolean> tEntry : mSessionPool.entrySet())
        {
            // clean up older sessions
            if (tEntry.getKey().isOlderThan(pSessionExpiryTime))
            {
                removeSession(tEntry.getKey());
            }
        }
    }

    public ConcurrentHashMap<JMSQueueSession, Boolean> getSessionPool()
    {
        return mSessionPool;
    }
}
