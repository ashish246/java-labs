package com.concurrency.collection;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Collections classes such as Vector and Hashtable are thread-safe. You can
 * make other classes (like ArrayList) thread-safe by using synchronized wrapper
 * factory methods such as Collections.synchronizedMap(),
 * Collections.synchronizedList(), and Collections.synchronizedSet().
 * 
 * There are a couple of problems with the thread-safe collections:
 * 
 * 1) Code that iterates over a collection that might be modified by another
 * thread during the iteration requires a lock to avoid a thrown
 * java.util.ConcurrentModificationException. This requirement is necessary
 * because Collections framework classes return fail-fast iterators, which are
 * iterators that throw ConcurrentModificationException when a collection is
 * modified during iteration. Fail-fast iterators are often an inconvenience to
 * concurrent applications.
 * 
 * 2) Performance often suffers when these synchronized collections are accessed
 * frequently from multiple threads; this is a performance problem that impacts
 * an application's scalability.
 * 
 * CopyOnWriteArrayList<E>: This class describes a thread-safe variant of
 * ArrayList in which all mutative operations (e.g., add and set) are
 * implemented by making a fresh copy of the underlying array whenever an
 * element is added or removed. However, in-progress iterations continue to work
 * on the previous copy (when the iterator was created). Although there's some
 * cost to copying the array, this cost is acceptable in situations where there
 * are many more iterations than modifications.
 * 
 * @author Administrator
 * 
 */
public class CopyOnWriteArrayListDemo {
	public static void main(String[] args) {
		List<String> empList = new ArrayList<>();
		empList.add("John Doe");
		empList.add("Jane Doe");
		empList.add("Rita Smith");
		Iterator<String> empIter = empList.iterator();
		while (empIter.hasNext())
			try {
				System.out.println(empIter.next());
				if (!empList.contains("Tom Smith"))
					empList.add("Tom Smith");
			} catch (ConcurrentModificationException cme) {
				System.err.println("attempt to modify list during iteration");
				break;
			}

		List<String> empList2 = new CopyOnWriteArrayList<>();
		empList2.add("John Doe");
		empList2.add("Jane Doe");
		empList2.add("Rita Smith");
		empIter = empList2.iterator();
		while (empIter.hasNext()) {
			System.out.println(empIter.next());
			if (!empList2.contains("Tom Smith"))
				empList2.add("Tom Smith");
		}

	}
}