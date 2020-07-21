package com.concurrency.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * java.util.concurrent.BlockingQueue is a Queue that supports operations that
 * wait for the queue to become non-empty when retrieving and removing an
 * element, and wait for space to become available in the queue when adding an
 * element.
 * 
 * BlockingQueue doesn’t accept null values and throw NullPointerException if
 * you try to store null value in the queue.
 * 
 * BlockingQueue implementations are thread-safe. All queuing methods are atomic
 * in nature and use internal locks or other forms of concurrency control.
 * 
 * BlockingQueue interface is part of java collections framework and it’s
 * primarily used for implementing producer consumer problem. We don’t need to
 * worry about waiting for the space to be available for producer or object to
 * be available for consumer in BlockingQueue as it’s handled by implementation
 * classes of BlockingQueue.
 * 
 * A BlockingQueue has 4 different sets of methods for inserting, removing and
 * examining the elements in the queue. Each set of methods behaves differently
 * in case the requested operation cannot be carried out immediately. Here is a
 * table of the methods:
 * 
 * The 4 different sets of behaviour means this:
 * 
 * (1) Throws Exception: If the attempted operation is not possible immediately,
 * an exception is thrown. (2) Special Value: If the attempted operation is not
 * possible immediately, a special value is returned (often true / false). (3)
 * Blocks: If the attempted operation is not possible immedidately, the method
 * call blocks until it is. (4) Times Out: If the attempted operation is not
 * possible immedidately, the method call blocks until it is, but waits no
 * longer than the given timeout. Returns a special value telling whether the
 * operation succeeded or not (typically true / false).
 * 
 * 
 * While implementing producer consumer problem, we will use ArrayBlockingQueue
 * implementation and following methods are important to know.
 * 
 * 1) put(E e): This method is used to insert elements to the queue, if the
 * queue is full it waits for the space to be available. *
 * 
 * 2) E take(): This method retrieves and remove the element from the head of
 * the queue, if queue is empty it waits for the element to be available.
 * 
 * @author Administrator
 * 
 */
public class ProducerConsumerBlockingQueueExample {
	public static void main(String[] args) throws Exception {

		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
	}
}

class Producer implements Runnable {

	protected BlockingQueue<String> queue = null;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			queue.put("1");
			Thread.sleep(1000);
			queue.put("2");
			Thread.sleep(1000);
			queue.put("3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {

	protected BlockingQueue<String> queue = null;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}