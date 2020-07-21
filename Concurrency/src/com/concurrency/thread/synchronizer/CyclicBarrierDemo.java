package com.concurrency.thread.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A cyclic barrier is a thread-synchronization construct that lets a set of
 * threads wait for each other to reach a common barrier point. The barrier is
 * called cyclic because it can be re-used after the waiting threads are
 * released.
 * 
 * Cyclic barriers can be used to perform lengthy calculations by breaking them
 * into smaller individual tasks (as demonstrated by CyclicBarrier's Javadoc
 * example code). They're also used in multiplayer games that cannot start until
 * the last player has joined.
 * 
 * A cyclic barrier is implemented by the java.lang.concurrent.CyclicBarrier
 * class. This class provides the following constructors:
 * 
 * 1) CyclicBarrier(int nthreads, Runnable barrierAction) causes a maximum of
 * nthreads-1 threads to wait at the barrier. When one more thread arrives, it
 * executes the nonnull barrierAction and then all threads proceed. This action
 * is useful for updating shared state before any of the threads continue.
 * 
 * 2) CyclicBarrier(int nthreads) is similar to the previous constructor except
 * that no runnable is executed when the barrier is tripped.
 * 
 * CyclicBarrier declares an int await() method that typically causes the
 * calling thread to wait unless the thread is the final thread. If so, and if a
 * nonnull Runnable was passed to barrierAction, the final thread executes the
 * runnable before the other threads continue.
 * 
 * To reuse a CyclicBarrier instance, invoke its void reset() method.
 * 
 * @author Administrator
 * 
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		Runnable action = new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.printf("Thread %s " + "executing barrier action.%n",
						name);
			}
		};
		final CyclicBarrier barrier = new CyclicBarrier(3, action);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.printf("%s about to join game...%n", name);
				try {
					barrier.await();
				} catch (BrokenBarrierException bbe) {
					System.out.println("barrier is broken");
					return;
				} catch (InterruptedException ie) {
					System.out.println("thread interrupted");
					return;
				}
				System.out.printf("%s has joined game%n", name);
			}
		};
		ExecutorService[] executors = new ExecutorService[] {
				Executors.newSingleThreadExecutor(),
				Executors.newSingleThreadExecutor(),
				Executors.newSingleThreadExecutor() };
		for (ExecutorService executor : executors) {
			executor.execute(task);
			executor.shutdown();
		}
	}
}