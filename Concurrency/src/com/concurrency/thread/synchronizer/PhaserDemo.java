package com.concurrency.thread.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

/**
 * A phaser is a thread-synchronization construct that's similar to a cyclic
 * barrier in that it lets a group of threads wait on a barrier and then proceed
 * after the last thread arrives. It also offers the equivalent of a barrier
 * action. However, a phaser is more flexible.
 * 
 * Unlike a cyclic barrier, which coordinates a fixed number of threads, a
 * phaser can coordinate a variable number of threads, which can register at any
 * time. To implement this capability, a phaser takes advantage of phases and
 * phase numbers.
 * 
 * A phase is the phaser's current state, and this state is identified by an
 * integer-based phase number. When the last of the registered threads arrives
 * at the phaser barrier, a phaser advances to the next phase and increments its
 * phase number by 1.
 * 
 * <ul>
 * <li>The Phaser(int threads) constructor creates a phaser that initially
 * coordinates nthreads threads (which have yet to arrive at the phaser barrier)
 * and whose phase number is initially set to 0.</li>
 * <li>The int register() method adds a new unarrived thread to this phaser and
 * returns the phase number to which the arrival applies. This number is known
 * as the arrival phase number.</li>
 * <li>The int arriveAndAwaitAdvance() method records arrival and waits for the
 * phaser to advance (which happens after the other threads have arrived). It
 * returns the phase number to which the arrival applies.</li>
 * <li>The int arriveAndDeregister() method arrives at this phaser and
 * deregisters from it without waiting for others to arrive, reducing the number
 * of threads required to advance in future phases.</li>
 * </ul>
 * 
 * @author Administrator
 * 
 */
public class PhaserDemo {
	public static void main(String[] args) {
		List<Runnable> tasks = new ArrayList<>();
		
		tasks.add(new Runnable() {
			@Override
			public void run() {
				System.out.printf("%s running at %d%n", Thread.currentThread()
						.getName(), System.currentTimeMillis());
			}
		});
		tasks.add(new Runnable() {
			@Override
			public void run() {
				System.out.printf("%s running at %d%n", Thread.currentThread()
						.getName(), System.currentTimeMillis());
			}
		});
		runTasks(tasks);
	}

	static void runTasks(List<Runnable> tasks) {
		final Phaser phaser = new Phaser(1); // "1" to register self
		// create and start threads
		for (final Runnable task : tasks) {
			phaser.register();
			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(50 + (int) (Math.random() * 300));
					} catch (InterruptedException ie) {
						System.out.println("interrupted thread");
					}
					phaser.arriveAndAwaitAdvance(); // await all creation
					task.run();
				}
			}.start();
		}

		// allow threads to start and deregister self
		phaser.arriveAndDeregister();
	}
}
