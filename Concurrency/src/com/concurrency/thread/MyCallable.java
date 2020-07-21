package com.concurrency.thread;

import java.util.concurrent.Callable;

/**
 * In case the threads should return some value (result-bearing threads) then
 * you can use the java.util.concurrent.Callable class. The Callable object uses
 * generics to define the type of object which is returned.If you submit a
 * Callable object to an Executor the framework returns an object of type
 * java.util.concurrent.Future. This Future object can be used to check the
 * status of a Callable and to retrieve the result from the Callable.
 * 
 * @author Administrator
 * 
 */
public class MyCallable implements Callable<Long> {
	@Override
	public Long call() throws Exception {
		long sum = 0;
		for (long i = 0; i <= 100; i++) {
			sum += i;
		}
		return sum;
	}

}