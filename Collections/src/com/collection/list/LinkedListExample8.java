package com.collection.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Using ListIterator
 * 
 * @author Administrator
 * 
 */
public class LinkedListExample8 {
	public static void main(String args[]) {
		String elements[] = { "Huda Tutorials", "Java Tutorials", "C Tutorials" };
		LinkedList s; // java.util.LinkedList DECLARATION

		// java.util.List OBJECT CREATION USING ARRAY AS LIST
		s = new LinkedList(Arrays.asList(elements));

		// java.util.List OUTPUT
		ListIterator itr = s.listIterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		System.out.println();
		System.out.println("----- Reverse Order -----");
		System.out.println();

		// java.util.List OUTPUT IN REVERSE ORDER
		Iterator itr2 = s.descendingIterator();
		while (itr2.hasNext()) {
			System.out.println(itr2.next());
		}
	}
}