package com.concurrency.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * The "Runnable" is the task to perform. The Thread is the worker who is doing
 * this task. Using the Thread class directly has the following disadvantages:
 * <ul>
 * <li>Creating a new thread causes some performance overhead</li>
 * <li>Too many threads can lead to reduced performance, as the CPU needs to
 * switch between these threads.</li>
 * <li>You cannot easily control the number of threads, therefore you may run
 * into out of memory errors due to too many threads.</li>
 * </ul>
 * 
 * @author Administrator
 * 
 */
public class Main {

	public static void main(String[] args) {
		// We will store the threads so that we can check if they are done
		List<Thread> threads = new ArrayList<Thread>();
		// We will create 500 threads
		for (int i = 0; i < 500; i++) {
			Runnable task = new MyRunnable(10000000L + i);
			Thread worker = new Thread(task);
			// We can set the name of the thread
			worker.setName(String.valueOf(i));
			// Start the thread, never call method run() direct
			worker.start();
			// Remember the thread for later usage
			threads.add(worker);
		}
		int running = 0;
		do {
			running = 0;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					running++;
				}
			}
			System.out.println("We have " + running + " running threads. ");
		} while (running > 0);

	}
}