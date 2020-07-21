package com.concurrency.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * Data structure for a web crawler. Keeps track of the visited sites and keeps
 * a list of sites which needs still to be crawled.
 * 
 * The Java virtual machine (JVM) supports this mechanism via monitors and the
 * associated monitorenter and monitorexit instructions.
 * 
 * Each Java object is associated with a monitor, which is a mutual exclusion
 * mechanism that prevents multiple threads from concurrently executing in a
 * critical section. Before a thread can enter this section, it must lock the
 * monitor. If the monitor is already locked, the thread blocks until the
 * monitor is unlocked.
 * 
 * Monitors also address the vagaries of memory caching and compiler
 * optimizations that might otherwise prevent one thread from observing another
 * thread's update of a shared variable. Before a thread leaves the critical
 * section, the monitor ensures that the thread's updates are immediately
 * visible, so that another thread about to enter the critical section will see
 * those updates.
 * 
 * Synchronization supports mutual exclusion and visibility. In contrast, the
 * volatile keyword only supports visibility.
 * 
 * Although adequate for simple applications, Java's low-level synchronization
 * mechanism can be inconvenient for advanced applications that require
 * additional capabilities such as timed waits and lock polling. The Locking
 * framework, which is found in java.util.concurrent.locks, addresses these
 * limitations.
 * 
 * @author Administrator
 * 
 */

public class CrawlSites {
	private List<String> crawledSites = new ArrayList<String>();
	private List<String> linkedSites = new ArrayList<String>();

	public void add(String site) {
		synchronized (this) {
			if (!crawledSites.contains(site)) {
				linkedSites.add(site);
			}
		}
	}

	/**
	 * Get next site to crawl. Can return null (if nothing to crawl)
	 */

	public String next() {
		if (linkedSites.size() == 0) {
			return null;
		}
		synchronized (this) {
			// Need to check again if size has changed
			if (linkedSites.size() > 0) {
				String s = linkedSites.get(0);
				linkedSites.remove(0);
				crawledSites.add(s);
				return s;
			}
			return null;
		}
	}

	public synchronized void critial() {
		// some thread critical stuff
		// here
	}

}