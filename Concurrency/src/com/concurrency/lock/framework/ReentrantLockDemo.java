package com.concurrency.lock.framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The ReentrantLock class implements Lock and describes a reentrant mutual
 * exclusion lock. The lock is associated with an acquisition count. When a
 * thread holds the lock and re-acquires the lock, the acquisition count is
 * incremented and the lock must be released twice.
 * 
 * ReentrantLock offers the same concurrency and memory semantics as the
 * implicit monitor lock normally accessed using synchronized methods and
 * statements. However, it has extended capabilities and offers better
 * performance under high thread contention (that is, when threads are
 * frequently asking to acquire a lock that is already held by another thread)
 * 
 * ReentrantLock behaves like synchronized and you might wonder when it's
 * appropriate to use one or the other. Use ReentrantLock when you need timed or
 * interruptible lock waits, non-block-structured locks (obtain a lock in one
 * method; return the lock in another), multiple condition variables, or lock
 * polling. Furthermore, ReentrantLock supports scalability and is useful where
 * there is high contention among threads. If none of these factors come into
 * play, use synchronized.
 * 
 * ReentrantLock declares the following constructors:
 * 
 * 1) ReentrantLock() creates a reentrant lock.
 * 
 * 2) ReentrantLock(boolean fair) creates a reentrant lock with the given
 * fairness policy. Passing true to fair results in a lock that uses a fair
 * ordering policy, which means that under contention, the lock favors granting
 * access to the longest-waiting thread. The former constructor invokes this
 * constructor, passing false to fair.
 * 
 * int getHoldCount() returns the number of holds on this lock by the current
 * thread: a thread has a hold on a lock for each lock action that isn't matched
 * by an unlock action. When the lock() method is called and the current thread
 * already holds the lock, the hold count is incremented by one and the method
 * returns immediately.
 * 
 * You previously learned that ReentrantLock offers better performance than
 * synchronized under high thread contention. To boost performance,
 * ReentrantLock's synchronization is managed by a subclass of the abstract
 * java.util.concurrent.locks.AbstractQueuedSynchronizer class. In turn, this
 * class leverages the undocumented sun.misc.Unsafe class and its
 * compareAndSwapInt() CAS method.
 * 
 * @author Administrator
 * 
 */
public class ReentrantLockDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		final ReentrantLock rl = new ReentrantLock();

		class Worker implements Runnable {
			private String name;

			Worker(String name) {
				this.name = name;
			}

			@Override
			public void run() {
				rl.lock();
				rl.lock();// increase the hold count to 2
				try {
					if (rl.isHeldByCurrentThread())
						System.out
								.printf("Thread %s has entered its critical section.%n",
										name);
					System.out.printf(
							"Thread %s is performing work for 2 seconds.%n",
							name);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
					System.out
							.printf("Thread %s has finished working.%n", name);
				} finally {
					rl.unlock();
					rl.unlock();// because hold count was 2
				}
			}
		}

		executor.execute(new Worker("A"));
		executor.execute(new Worker("B"));

		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		executor.shutdownNow();
	}
}
