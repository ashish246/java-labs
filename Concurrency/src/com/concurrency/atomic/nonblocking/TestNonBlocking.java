package com.concurrency.atomic.nonblocking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.concurrency.atomic.Counter;

/**
 * Java 5.0 provides supports for additional atomic operations. This allows to
 * develop algorithm which are non-blocking algorithm, e.g. which do not require
 * synchronization, but are based on low-level atomic hardware primitives such
 * as compare-and-swap (CAS). A compare-and-swap operation check if the variable
 * has a certain value and if it has this value it will perform this operation.
 * 
 * Non-blocking algorithm are usually much faster then blocking algorithms as
 * the synchronization of threads appears on a much finer level (hardware).
 * 
 * Nonblocking algorithms are concurrent algorithms that derive their thread
 * safety not from locks, but from low-level atomic hardware primitives such as
 * compare-and-swap.
 * 
 * @author Administrator
 * 
 */
public class TestNonBlocking {
	private static final int NTHREDS = 10;

	public static void main(String[] args) {
		final Counter counter = new Counter();
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();

		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (int i = 0; i < 500; i++) {

			Callable<Integer> worker = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int number = counter.increment();
					System.out.println(number);
					return number;
				}
			};
			Future<Integer> submit = executor.submit(worker);
			list.add(submit);
		}

		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
		}

		Set<Integer> set = new HashSet<Integer>();
		for (Future<Integer> future : list) {
			try {
				set.add(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		if (list.size() != set.size()) {
			throw new RuntimeException("Double-entries!!!");
		}

	}

}