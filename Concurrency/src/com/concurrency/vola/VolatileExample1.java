package com.concurrency.vola;

/**
 * Pattern #2: one-time safe publication
 * 
 * One technique for safely publishing an object is to make the object reference
 * volatile.
 * 
 * @author Administrator
 * 
 */
public class VolatileExample1 {
	public volatile Flooble theFlooble;

	public void initInBackground() {
		// do lots of stuff
		theFlooble = new Flooble(); // this is the only write to theFlooble
	}
}

class SomeOtherClass {
	public void doWork() {
		while (true) {
			VolatileExample1 floobleLoader = new VolatileExample1();
			// do some stuff...
			// use the Flooble, but only if it is ready
			if (floobleLoader.theFlooble != null) {
				// doSomething(floobleLoader.theFlooble);
			}
		}
	}
}

class Flooble {

}
