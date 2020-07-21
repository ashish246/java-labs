package com.concurrency.thread.synchronizer;

import java.util.concurrent.CountDownLatch;

/**
 * A countdown latch is a thread-synchronization construct that causes one or
 * more threads to wait until a set of operations being performed by other
 * threads finishes. It consists of a count and
 * "cause a thread to wait until the count reaches zero" and
 * "decrement the count" operations.
 * 
 * The java.util.concurrent.CountDownLatch class implements a countdown latch.
 * Its CountDownLatch(int count) constructor initializes the countdown latch to
 * the specified count. A thread invokes the void await() method to wait until
 * the count has reached zero (or the thread has been interrupted). Subsequent
 * calls to await() for a zero count return immediately. A thread calls void
 * countDown() to decrement the count.
 * 
 * Countdown latches are useful for decomposing a problem into smaller pieces
 * and giving a piece to a separate thread.
 * 
 * <ul>
 * <li>A main thread creates a countdown latch with a count of 1 that's used as
 * a "starting gate" to start a group of worker threads simultaneously.</li>
 * <li>Each worker thread waits on the latch and the main thread decrements this
 * latch to let all worker threads proceed.</li>
 * <li>The main thread waits on another countdown latch initialized to the
 * number of worker threads.</li>
 * <li>When a worker thread completes, it decrements this count. After the count
 * reaches zero (meaning that all worker threads have finished), the main thread
 * proceeds and gathers the results.</li>
 * 
 * </ul>
 * 
 * @author Administrator
 * 
 */
public class CountDownLatchDemo {
	final static int N = 3;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);
		for (int i = 0; i < N; ++i)
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();
		System.out.println("about to let threads proceed");
		startSignal.countDown(); // let all threads proceed
		System.out.println("doing work");
		System.out.println("waiting for threads to finish");
		doneSignal.await(); // wait for all threads to finish
		System.out.println("main thread terminating");
	}
}

class Worker implements Runnable {
	private final static int N = 5;

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	@Override
	public void run() {
		try {
			String name = Thread.currentThread().getName();
			startSignal.await();
			for (int i = 0; i < N; i++) {
				System.out.printf("thread %s is working%n", name);
				try {
					Thread.sleep((int) (Math.random() * 300));
				} catch (InterruptedException ie) {
				}
			}
			System.out.printf("thread %s finishing%n", name);
			doneSignal.countDown();
		} catch (InterruptedException ie) {
			System.out.println("interrupted");
		}
	}
}