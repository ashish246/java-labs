package com.concurrency.atomic.nonblocking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * one of the simplest nonblocking algorithms: a counter that uses the
 * compareAndSet() (CAS) method of AtomicInteger. The compareAndSet() method
 * says
 * "Update this variable to this new value, but fail if some other thread changed the value since I last looked."
 * 
 * Modern processors provide special instructions for atomically updating shared
 * data that can detect interference from other threads, and compareAndSet()
 * uses these instead of locking.
 * 
 * It synchronizes at a finer level of granularity (an individual memory
 * location) using a hardware primitive instead of the JVM locking code path,
 * and losing threads can retry immediately rather than being suspended and
 * rescheduled. The finer granularity reduces the chance that there will be
 * contention, and the ability to retry without being descheduled reduces the
 * cost of contention. Even with a few failed CAS operations, this approach is
 * still likely to be faster than being rescheduled because of lock contention.
 * 
 * Nonblocking algorithms are often called optimistic because they proceed with
 * the assumption that there will be no interference. If interference is
 * detected, they back off and retry. In the case of the counter, the
 * speculative step is the increment -- it fetches and adds one to the old value
 * in the hopes that the value will not change while the update is being
 * computed. If it is wrong, it has to fetch the value again and redo the
 * increment computation.
 * 
 * @author Administrator
 * 
 */
public class NonblockingCounter {
	private AtomicInteger value;

	public int getValue() {
		return value.get();
	}

	public int increment() {
		int v;
		do {
			v = value.get();
		} while (!value.compareAndSet(v, v + 1));
		return v + 1;
	}
}