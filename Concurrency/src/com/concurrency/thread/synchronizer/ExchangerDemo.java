package com.concurrency.thread.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * An exchanger (also known as a rendezvous) is a thread-synchronization
 * construct that lets a pair of threads exchange data items. An exchanger is
 * similar to a cyclic barrier whose count is set to 2 but also supports
 * exchange of data when both threads reach the barrier.
 * 
 * The java.util.concurrent.Exchanger<V> class implements an exchanger. This
 * class provides an Exchanger() constructor for initializing an exchanger that
 * describes an exchange point and a pair of exchange() methods for performing
 * an exchange.
 * 
 * Exchanger's Javadoc states that this synchronizer may be useful in genetic
 * algorithms and pipeline designs, where one thread fills a buffer and the
 * other thread empties the buffer. When both threads meet at the exchange
 * point, they swap their buffers.
 * 
 * when the filling thread's currentBuffer.isFull() expression is true, it
 * executes currentBuffer = exchanger.exchange(currentBuffer) and waits. The
 * emptying thread continues until currentBuffer.isEmpty() evaluates to true,
 * and also invokes exchange(currentBuffer). At this point, the buffers are
 * swapped and the threads continue.
 * 
 * @author Administrator
 * 
 */
public class ExchangerDemo {
	static Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
	static DataBuffer initialEmptyBuffer = new DataBuffer();
	static DataBuffer initialFullBuffer = new DataBuffer("ITEM");

	public static void main(String[] args) {

		class FillingLoop implements Runnable {
			int count = 0;

			@Override
			public void run() {
				DataBuffer currentBuffer = initialEmptyBuffer;
				try {
					while (true) {
						addToBuffer(currentBuffer);
						if (currentBuffer.isFull()) {
							System.out
									.println("filling loop thread wants to exchange");
							currentBuffer = exchanger.exchange(currentBuffer);
							System.out
									.println("filling loop thread observes an exchange");
						}
					}
				} catch (InterruptedException ie) {
					System.out.println("filling loop thread interrupted");
				}
			}

			void addToBuffer(DataBuffer buffer) {
				String item = "NEWITEM" + count++;
				System.out.printf("Adding %s%n", item);
				buffer.add(item);
			}
		}

		class EmptyingLoop implements Runnable {
			@Override
			public void run() {
				DataBuffer currentBuffer = initialFullBuffer;
				try {
					while (true) {
						takeFromBuffer(currentBuffer);
						if (currentBuffer.isEmpty()) {
							System.out
									.println("emptying loop thread wants to exchange");
							currentBuffer = exchanger.exchange(currentBuffer);
							System.out
									.println("emptying loop thread observes an exchange");
						}
					}
				} catch (InterruptedException ie) {
					System.out.println("emptying loop thread interrupted");
				}
			}

			void takeFromBuffer(DataBuffer buffer) {
				System.out.printf("taking %s%n", buffer.remove());
			}
		}

		new Thread(new EmptyingLoop()).start();
		new Thread(new FillingLoop()).start();
	}
}

class DataBuffer {
	private final static int MAX = 10;
	private List<String> items = new ArrayList<>();

	DataBuffer() {
	}

	DataBuffer(String prefix) {
		for (int i = 0; i < MAX; i++) {
			String item = prefix + i;
			System.out.printf("Adding %s%n", item);
			items.add(item);
		}
	}

	void add(String s) {
		if (!isFull())
			items.add(s);
	}

	boolean isEmpty() {
		return items.size() == 0;
	}

	boolean isFull() {
		return items.size() == MAX;
	}

	String remove() {
		if (!isEmpty())
			return items.remove(0);
		return null;
	}
}