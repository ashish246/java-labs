package com.concurrency.atomic;

/**
 * The following class uses EmulatedCAS to implement a non-synchronized counter
 * (pretend that EmulatedCAS doesn't require synchronized).
 * 
 * increment() repeatedly invokes compareAndSwap() until readValue's value
 * doesn't change. It's then free to change this value. When no lock is
 * involved, contention is avoided along with excessive context switching.
 * Performance improves and the code is more scalable.
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
public class Counter {
	private EmulatedCAS value = new EmulatedCAS();

	public int getValue() {
		return value.getValue();
	}

	public int increment() {
		int readValue = value.getValue();
		while (value.compareAndSwap(readValue, readValue + 1) != readValue)
			readValue = value.getValue();
		return readValue + 1;
	}
}