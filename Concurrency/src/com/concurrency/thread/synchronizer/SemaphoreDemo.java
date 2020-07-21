package com.concurrency.thread.synchronizer;

/**
 * A semaphore is a thread-synchronization construct for controlling thread
 * access to a common resource. It's often implemented as a protected variable
 * whose value is incremented by an acquire operation and decremented by a
 * release operation.
 * 
 * Semaphores whose current values can be incremented past 1 are known as
 * counting semaphores, whereas semaphores whose current values can be only 0 or
 * 1 are known as binary semaphores or mutexes. In either case, the current
 * value cannot be negative.
 * 
 * Each call to the Semaphore's void acquire() method takes one of the available
 * permits or blocks the calling thread when one isn't available. Each call to
 * Semaphore's void release() method returns an available permit, potentially
 * releasing a blocking acquirer thread.
 * 
 * @author Administrator
 * 
 */
public class SemaphoreDemo {

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				Person p1 = new Person(1L, "Test1", "XYZ");
				try {
					PersonLock.getInstance().getWriteLock();
					PersonStorage.getInstance().putPerson(p1);
				} catch (InterruptedException e) {
					// Exception handling need to be done
					e.printStackTrace();
				} finally {
					PersonLock.getInstance().releaseWriteLock();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				Person p2 = new Person(2L, "Test123", "ABC");

				try {
					PersonLock.getInstance().getWriteLock();

					PersonStorage.getInstance().putPerson(p2);
				} catch (InterruptedException e) {
					// Exception handling need to be done
				} finally {
					PersonLock.getInstance().releaseWriteLock();
				}

			}
		});

		t1.start();
		t2.start();

		System.out.println(PersonStorage.getInstance().retrievePerson(2));
	}
}
