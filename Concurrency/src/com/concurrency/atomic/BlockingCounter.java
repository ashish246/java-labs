package com.concurrency.atomic;

/**
 * Counter in Listing 1 is thread-safe, but the need to use a lock irks some
 * developers because of the performance cost involved. But the lock is needed
 * because increment, though it looks like a single operation, is shorthand for
 * three separate operations: fetch the value, add one to it, and write the
 * value out. (Synchronization is also needed on the getValue method, to ensure
 * that threads calling getValue see an up-to-date value. Simply ignoring the
 * need for locking is not a good strategy, though many developers seem
 * disturbingly willing to convince themselves that this approach is
 * acceptable.)
 * 
 * When multiple threads ask for the same lock at the same time, one wins and
 * acquires the lock, and the others block. JVMs typically implement blocking by
 * suspending the blocked thread and rescheduling it later. The resulting
 * context switches can cause a significant delay relative to the few
 * instructions protected by the lock.
 * 
 * @author Administrator
 * 
 */
public final class BlockingCounter {
	private long value = 0;

	public synchronized long getValue() {
		return value;
	}

	public synchronized long increment() {
		return ++value;
	}
}