package com.concurrency.atomic;

/**
 * Multithreaded applications that run on multicore processors or multiprocessor
 * systems can achieve good hardware utilization and be highly scalable.
 * 
 * Java's traditional synchronization mechanism, which enforces mutual exclusion
 * (the thread holding the lock that guards a set of variables has exclusive
 * access to them) and visibility (changes to the guarded variables become
 * visible to other threads that subsequently acquire the lock), impacts
 * hardware utilization and scalability, as follows:
 * 
 * 1) Contended synchronization (multiple threads constantly competing for a
 * lock) is expensive and throughput suffers as a result. A major reason for the
 * expense is the frequent context switching that takes place; a context switch
 * operation can take many processor cycles to complete. In contrast,
 * uncontended synchronization is inexpensive on modern JVMs.
 * 
 * 2) When a thread holding a lock is delayed (e.g., because of a scheduling
 * delay), no thread that requires that lock makes any progress, and the
 * hardware isn't utilized as well as it otherwise might be.
 * 
 * You might think that you can use volatile as a synchronization alternative.
 * However, volatile variables only solve the visibility problem. They cannot be
 * used to safely implement the atomic read-modify-write sequences that are
 * necessary for safely implementing counters and other entities that require
 * mutual exclusion.
 * 
 * **This atomic variable alternative is based on a microprocessor's
 * compare-and-swap instructions.
 * 
 * The compare-and-swap (CAS) instruction is an uninterruptible instruction that
 * reads a memory location, compares the read value with an expected value, and
 * stores a new value in the memory location when the read value matches the
 * expected value. Otherwise, nothing is done. The actual microprocessor
 * instruction may differ somewhat (e.g., return true if CAS succeeded or false
 * otherwise instead of the read value).
 * 
 * CAS makes it possible to support atomic read-modify-write sequences. You
 * would typically use CAS as follows:
 * <ul>
 * <li>Read value v from address X.</li>
 * <li>Perform a multistep computation to derive a new value v2.</li>
 * <li>Use CAS to change the value of X from v to v2. CAS succeeds when X's
 * value hasn't changed while performing these steps.</li>
 * </ul>
 * 
 * Java implements compareAndSet() via the fastest available native construct
 * (e.g., cmpxchg or load-link/store-conditional) or (in the worst case) spin
 * locks.
 * 
 * @author Administrator
 * 
 */
public class EmulatedCAS {
	private int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int readValue = value;
		if (readValue == expectedValue)
			value = newValue;
		return readValue;
	}
}