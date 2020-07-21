package com.concurrency.vola;

/**
 * You can use volatile variables instead of locks only under a restricted set
 * of circumstances. Both of the following criteria must be met for volatile
 * variables to provide the desired thread-safety:
 * <ul>
 * <li>Writes to the variable do not depend on its current value.</li>
 * <li>The variable does not participate in invariants with other variables.</li>
 * </ul>
 * 
 * Pattern #1: status flags
 * 
 * Perhaps the canonical use of volatile variables is simple boolean status
 * flags, indicating that an important one-time life-cycle event has happened,
 * such as initialization has completed or shutdown has been requested.
 * 
 * @author Administrator
 * 
 */
public class VolatileExample {
	volatile boolean shutdownRequested;

	public void shutdown() {
		shutdownRequested = true;
	}

	public void doWork() {
		while (!shutdownRequested) {
			// do stuff
		}
	}
}
