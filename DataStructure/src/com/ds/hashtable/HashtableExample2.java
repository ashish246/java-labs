package com.ds.hashtable;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class HashtableExample2 {
	public static void main(String args[]) {
		// java.util.Hashtable DECLARATION
		Hashtable<String, Integer> h;

		// java.util.Hashtable OBJECT CREATION
		// USING DEFAULT CONSTRUCTOR
		h = new Hashtable<String, Integer>();

		// ADD AN ELEMENTS
		h.put("ONE", new Integer(1));
		h.put("TWO", new Integer(2));
		h.put("THREE", new Integer(3));

		// Hashtable KEYS OUTPUT
		Enumeration e = h.keys();
		int i = 1;
		while (e.hasMoreElements()) {
			System.out.println("Key " + i++ + " : " + e.nextElement());
		}

		// Hashtable VALUES OUTPUT
		Collection c = h.values();
		Iterator values = c.iterator();
		int j = 1;
		while (values.hasNext()) {
			System.out.println("Value " + j++ + " : " + values.next());
		}

		// Hashtable OUTPUT
		Enumeration e1 = h.elements();
		while (e1.hasMoreElements()) {
			System.out.println(e1.nextElement());
		}
	}
}
