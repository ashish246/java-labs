package com.concurrency.lock.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * You'll occasionally encounter a situation where data structures are read more
 * often than they're modified. The Locking framework has a read-write locking
 * mechanism for these situations that yields both greater concurrency when
 * reading and the safety of exclusive access when writing.
 * 
 * The ReadWriteLock interface maintains a pair of associated locks, one for
 * read-only operations and one for write operations. The read lock may be held
 * simultaneously by multiple reader threads as long as there are no writers.
 * The write lock is exclusive: only a single thread can modify shared data.
 * (The lock that's associated with the synchronized keyword is also exclusive.)
 * 
 * ReadWriteLock declares the following methods:
 * <ul>
 * <li>Lock readLock() returns the lock that's used for reading.</li>
 * <li>Lock writeLock() returns the lock that's used for writing.</li>
 * <li>int getQueueLength() returns an estimate of the number of threads waiting
 * to acquire either the read or write lock.</li>
 * <li>int getReadHoldCount() returns the number of read holds on this lock by
 * the current thread. A reader thread has a hold on a lock for each lock action
 * that is not matched by an unlock action</li>
 * <li>boolean hasWaiters(Condition condition) returns true when there are
 * threads waiting on the given condition associated with the write lock</li>
 * </ul>
 * 
 * @author Administrator
 * 
 */
public class ReadWriteLockDemo {
	final static int DELAY = 80;
	final static int NUMITER = 5;

	public static void main(String[] args) {
		final Names names = new Names();

		/**
		 * NamedThread is a local class that is subsequently used in an executor
		 * context to provide a name for the executor's thread. It implements
		 * the java.util.concurrent. ThreadFactory interface and its Thread
		 * newThread(Runnable r) method, which returns a new thread whose name
		 * was previously passed to the NamedThread(String name) constructor.
		 * 
		 * @author Administrator
		 * 
		 */
		class NamedThread implements ThreadFactory {
			private String name;

			NamedThread(String name) {
				this.name = name;
			}

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, name);
			}
		}

		ExecutorService writer;
		writer = Executors.newSingleThreadExecutor(new NamedThread("writer"));
		Runnable wrunnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < NUMITER; i++) {
					names.add(Thread.currentThread().getName(), "A" + i);
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException ie) {
					}
				}
			}
		};
		writer.submit(wrunnable);

		ExecutorService reader1;
		reader1 = Executors.newSingleThreadExecutor(new NamedThread("reader1"));
		ExecutorService reader2;
		reader2 = Executors.newSingleThreadExecutor(new NamedThread("reader2"));
		Runnable rrunnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < NUMITER; i++)
					names.dump(Thread.currentThread().getName());
			}
		};
		reader1.submit(rrunnable);
		reader2.submit(rrunnable);

		reader1.shutdown();
		reader2.shutdown();
		writer.shutdown();
	}
}

/**
 * Stores the list of names and provides methods for adding names to and dumping
 * the list.
 * 
 * @author Administrator
 * 
 */
class Names {
	private final List<String> names;

	private final ReentrantReadWriteLock lock;
	private final Lock readLock, writeLock;

	Names() {
		names = new ArrayList<>();
		lock = new ReentrantReadWriteLock();
		readLock = lock.readLock();
		writeLock = lock.writeLock();
	}

	void add(String threadName, String name) {
		writeLock.lock();
		try {
			System.out.printf("%s: num waiting (add) threads = %d%n", threadName,
					lock.getQueueLength());
			names.add(name);
		} finally {
			writeLock.unlock();
		}
	}

	void dump(String threadName) {
		readLock.lock();
		try {
			System.out.printf("%s: num waiting (dump) threads = %d%n", threadName,
					lock.getQueueLength());
			Iterator<String> iter = names.iterator();
			while (iter.hasNext()) {
				System.out.printf("%s: %s%n", threadName, iter.next());
				try {
					Thread.sleep((int) (Math.random() * 100));
				} catch (InterruptedException ie) {
				}
			}
		} finally {
			readLock.unlock();
		}
	}
}