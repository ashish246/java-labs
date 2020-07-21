package com.concurrency.atomic.nonblocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * For more sophisticated data structures, nonblocking algorithms are much more
 * complicated than these simple examples because modifying a linked list, tree,
 * or hash table can involve updating more than one pointer. CAS enables atomic
 * conditional updates on a single pointer, but not on two. So to construct a
 * nonblocking linked list, tree, or hash table, we need to find a way to update
 * multiple pointers with CAS without leaving the data structure in an
 * inconsistent state.
 * 
 * Inserting an element at the tail of a linked list typically involves updating
 * two pointers: the "tail" pointer that always refers to the last element in
 * the list and the "next" pointer from the previous last element to the newly
 * inserted element. Because two pointers need to be updated, two CASes are
 * needed. Updating two pointers in separate CAS operations introduces two
 * potential problems that need to be considered: what happens if the first CAS
 * succeeds but the second fails, and what happens if another thread attempts to
 * access the list between the first and second CAS.
 * 
 * The "trick" to building nonblocking algorithms for nontrivial data structures
 * is to make sure that the data structure is always in a consistent state, even
 * between the time that a thread starts modifying the data structure and the
 * time it finishes, and to make sure that other threads can tell not only
 * whether the first thread has finished its update or is still in the middle of
 * it, but also what operations would be required to complete the update if the
 * first thread went AWOL. If a thread arrives on the scene to find the data
 * structure in the middle of an update, it can "help" the thread already
 * performing the update by finishing the update for it, and then proceeding
 * with its own operation. When the first thread gets around to trying to finish
 * its own update, it will realize that the work is no longer necessary and just
 * return because the CAS will detect the interference (in this case,
 * constructive interference) from the helping thread.
 * 
 * This "help thy neighbor" requirement is needed to make the data structure
 * resistant to the failure of individual threads. If a thread arrived to find
 * the data structure in mid-update by another thread and just waited until that
 * thread finished its update, it could wait forever if the other thread fails
 * in the middle of its operation. Even in the absence of failure, this approach
 * would offer poor performance because the newly arriving thread would have to
 * yield the processor, incurring a context switch, or wait for its quantum to
 * expire, which is even worse.
 * 
 * If you dive into the JVM and OS, you'll find nonblocking algorithms
 * everywhere. The garbage collector uses them to accelerate concurrent and
 * parallel garbage collection; the scheduler uses them to efficiently schedule
 * threads and processes and to implement intrinsic locking. In Mustang (Java
 * 6.0), the lock-based SynchronousQueue algorithm is being replaced with a new
 * nonblocking version. Few developers use SynchronousQueue directly, but it is
 * used as the work queue for thread pools constructed with the
 * Executors.newCachedThreadPool() factory.
 * 
 * The queue is in the quiescent state before an insertion operation and after
 * the second CAS (D) succeeds; it is in the intermediate state after the first
 * CAS (C) succeeds. In the quiescent state, the next field of the link node
 * pointed to by the tail is always null; in the intermediate state, it is
 * always non-null. Any thread can tell which state the queue is in by comparing
 * tail.next to null, which is the key to enabling threads to help other threads
 * "finish" their operation.
 *
 * The insertion operation first checks to see if the queue is in the
 * intermediate state before attempting to insert a new element (A), as shown in
 * Listing 4. If it is, then some other thread must already be in the middle of
 * inserting an element, between steps (C) and (D). Rather than wait for the
 * other thread to finish, the current thread can "help" it out by finishing the
 * operation for it by moving the tail pointer forward (B). It keeps checking
 * the tail pointer and advancing it if necessary until the queue is in the
 * quiescent state, at which point it can begin its own insertion.
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
public class NonblockingLinkedQueue<E> {
	private static class Node<E> {
		final E item;
		final AtomicReference<Node<E>> next;

		Node(E item, Node<E> next) {
			this.item = item;
			this.next = new AtomicReference<Node<E>>(next);
		}
	}

	private AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(
			new Node<E>(null, null));
	private AtomicReference<Node<E>> tail = head;

	public boolean put(E item) {
		Node<E> newNode = new Node<E>(item, null);
		while (true) {
			Node<E> curTail = tail.get();
			Node<E> residue = curTail.next.get();
			if (curTail == tail.get()) {
				if (residue == null) /* A - The insertion operation first checks to see if the queue is in the intermediate state before attempting to insert a new element */{
					if (curTail.next.compareAndSet(null, newNode)) /* C - linking the new node from the current last node on the queue */{
						tail.compareAndSet(curTail, newNode) /* D - swinging the tail pointer to point to the new last node */;
						return true;
					}
				} else {
					tail.compareAndSet(curTail, residue) /* B - the current thread can "help" it out by finishing the operation for it by moving the tail pointer forward*/;
				}
			}
		}
	}
}
