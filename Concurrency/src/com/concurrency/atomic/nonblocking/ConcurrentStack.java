package com.concurrency.atomic.nonblocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * The push() method observes the current top node, constructs a new node to be
 * pushed on the stack, and then, if the topmost node has not changed since the
 * initial observation, installs the new node. If the CAS fails, it means that
 * another thread has modified the stack, so the process starts again.
 * 
 * Under light to moderate contention, nonblocking algorithms tend to outperform
 * blocking ones because most of the time the CAS succeeds on the first try, and
 * the penalty for contention when it does occur does not involve thread
 * suspension and context switching, just a few more iterations of the loop. An
 * uncontended CAS is less expensive than an uncontended lock acquisition (this
 * statement has to be true because an uncontended lock acquisition involves a
 * CAS plus additional processing), and a contended CAS involves a shorter delay
 * than a contended lock acquisition.
 * 
 * Under high contention -- when many threads are pounding on a single memory
 * location -- lock-based algorithms start to offer better throughput than
 * nonblocking ones because when a thread blocks, it stops pounding and
 * patiently waits its turn, avoiding further contention. However, contention
 * levels this high are uncommon, as most of the time threads interleave
 * thread-local computation with operations that contend for shared data, giving
 * other threads a chance at the shared data. (Contention levels this high also
 * indicate that reexamining your algorithm with an eye towards less shared data
 * is in order.)
 * 
 * The first CAS (C) could fail because two threads are contending for access to
 * the current last element of the queue; in this case, no change has taken
 * effect, and any threads that lose the CAS reload the tail pointer and try
 * again. If the second CAS (D) fails, the inserting thread does not need to
 * retry -- because another thread has completed the operation for it in step
 * (B)!
 * 
 * @author Administrator
 * 
 * @param <E>
 */
public class ConcurrentStack<E> {
	AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();

	public void push(E item) {
		Node<E> newHead = new Node<E>(item);
		Node<E> oldHead;
		do {
			oldHead = head.get();
			newHead.next = oldHead;
		} while (!head.compareAndSet(oldHead, newHead));
	}

	public E pop() {
		Node<E> oldHead;
		Node<E> newHead;
		do {
			oldHead = head.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		} while (!head.compareAndSet(oldHead, newHead));
		return oldHead.item;
	}

	static class Node<E> {
		final E item;
		Node<E> next;

		public Node(E item) {
			this.item = item;
		}
	}
}
