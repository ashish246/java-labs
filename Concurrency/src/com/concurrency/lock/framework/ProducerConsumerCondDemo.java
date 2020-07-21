package com.concurrency.lock.framework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Condition interface factors out the java.lang.Object monitor methods
 * (wait(), notify(), and notifyAll()) into distinct objects to give the effect
 * of having multiple wait-sets per object, by combining them with the use of
 * arbitrary Lock implementations. Where Lock replaces synchronized methods and
 * statements, Condition replaces Object monitor methods.
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class ProducerConsumerCondDemo {

	public static void main(String[] args) {
		Shared s = new Shared();
		new Producer(s).start();
		new Consumer(s).start();
	}
}

/**
 * Shared encapsulates the logic for setting and getting a shared variable's
 * value.
 * 
 * The producer thread always invokes the void setSharedChar(char c) method to
 * generate a new character. This method locks the previously created lock
 * object and then enters a while loop that repeatedly tests variable available
 * -- this variable is true when a produced character hasn't yet been consumed.
 * 
 * As long as available is true, the producer invokes the condition's await()
 * method to wait for available to become false. The consumer will signal the
 * condition to wake up the producer when it has consumed the character. (A loop
 * is used instead of an if statement because spurious wakeups are possible and
 * available might still be true.)
 * 
 * After exiting the loop, the producer records the new character, assigns true
 * to available to indicate that a new character is available for consumption,
 * and signals the condition to wake up a waiting consumer. Lastly, it unlocks
 * the lock and returns from setSharedChar().
 * 
 * @author Administrator
 * 
 */
class Shared {
	// Fields c and available are volatile so that writes to them are visible to
	// the various threads. Fields lock and condition are final so that they're
	// initial values are visible to the various threads. (The Java memory model
	// promises that, after a final field has been initialized, any thread will
	// see the same [correct] value.).

	private volatile char c;
	private volatile boolean available;
	private final Lock lock;
	private final Condition condition;

	Shared() {
		c = '\u0000';
		available = false;
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}

	Lock getLock() {
		return lock;
	}

	char getSharedChar() {
		lock.lock();
		try {
			while (!available)
				try {
					condition.await();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			available = false;
			condition.signal();
		} finally {
			lock.unlock();
			return c;
		}
	}

	void setSharedChar(char c) {
		lock.lock();
		try {
			while (available)
				try {
					condition.await();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			this.c = c;
			available = true;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * The classic producer-consumer example nicely demonstrates conditions. In this
 * example, a producer thread repeatedly produces items for consumption by a
 * consumer thread.
 * 
 * The producer thread must not produce a new item until the previously produced
 * item has been consumed. Similarly, the consumer thread must not consume an
 * item that hasn't been produced. This is known as lockstep synchronization.
 * 
 * Why am I locking the get/print and set/print code blocks? Without this
 * locking, you might observe consuming messages before producing messages, even
 * though characters are produced before they're consumed. Locking these blocks
 * prevents this strange output order.
 * 
 * @author Administrator
 * 
 */
class Producer extends Thread {
	// l is final because it's initialized on the main thread and accessed on
	// the
	// producer thread.

	private final Lock l;

	// s is final because it's initialized on the main thread and accessed on
	// the
	// producer thread.

	private final Shared s;

	Producer(Shared s) {
		this.s = s;
		l = s.getLock();
	}

	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			l.lock();
			s.setSharedChar(ch);
			System.out.println(ch + " produced by producer.");
			l.unlock();
		}
	}
}

class Consumer extends Thread {
	// l is final because it's initialized on the main thread and accessed on
	// the
	// consumer thread.

	private final Lock l;

	// s is final because it's initialized on the main thread and accessed on
	// the
	// consumer thread.

	private final Shared s;

	Consumer(Shared s) {
		this.s = s;
		l = s.getLock();
	}

	@Override
	public void run() {
		char ch;
		do {
			l.lock();
			ch = s.getSharedChar();
			System.out.println(ch + " consumed by consumer.");
			l.unlock();
		} while (ch != 'Z');
	}
}
