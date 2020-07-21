package com.concurrency.thread;

/**
 * The join() method of a Thread instance can be used to “join” the start of a
 * thread’s execution to the end of another thread’s execution so that a thread
 * will not start running until another thread has ended. If join() is called on
 * a Thread instance, the currently running thread will block until the Thread
 * instance has finished executing.
 * 
 * @author Administrator
 * 
 */
public class JoinExample {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println("First task started");
				System.out.println("Sleeping for 2 seconds");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("First task completed");
			}
		});
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("task2 Sleeping for 2 seconds");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Second task completed");
			}
		});
		t.start(); // Line 15
		t.join(); // Line 16
		t1.start(); // t1 thread will not start until t thread is complete.
	}
}