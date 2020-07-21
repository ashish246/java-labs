package com.concurrency.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.concurrency.thread.MyRunnable;

/**
 * Thread pools manage a pool of worker threads. The thread pools contains a
 * work queue which holds tasks waiting to get executed.
 * 
 * A thread pool can be described as a collection of Runnable objects (work
 * queue) and a connections of running threads. These threads are constantly
 * running and are checking the work query for new work. If there is new work to
 * be done they execute this Runnable. The Thread class itself provides a
 * method, e.g. execute(Runnable r) to add a new Runnable object to the work
 * queue.
 * 
 * Regardless of whether an exception is thrown or not, the executor must be
 * shut down before the application exits. If the executor isn't shut down, the
 * application won't exit because the non-daemon thread-pool threads are still
 * executing.
 * 
 * @author Administrator
 * 
 */
public class Main {
	private static final int NTHREDS = 10;

	public static void main(String[] args) throws InterruptedException {
		/*
		 * If you want to use one thread pool with one thread which executes
		 * several runnables you can use the Executors.newSingleThreadExecutor()
		 * method.
		 */

		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (int i = 0; i < 500; i++) {
			Runnable worker = new MyRunnable(10000000L + i);
			executor.execute(worker);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		executor.awaitTermination(30, TimeUnit.SECONDS);
		System.out.println("Finished all threads");
	}
}
