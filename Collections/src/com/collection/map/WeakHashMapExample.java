package com.collection.map;

import java.util.WeakHashMap;

public class WeakHashMapExample
{
	public static void main(String args[])
	{
		//java.util.WeakHashMap DECLARATION
		WeakHashMap <String,Integer> h;

		//java.util.WeakHashMap OBJECT CREATION
		//USING DEFAULT CONSTRUCTOR
		h = new WeakHashMap <String,Integer>();

		// Create a key for the map, keep the strong reference
		String strongReference = new String("ONE");

		//ADD KEY AND VALUE
		h.put(strongReference, new Integer(1));
		h.put("TWO", new Integer(2));
		h.put("THREE", new Integer(3));

		//WeakHashMap OUTPUT BEFORE null the strongReference
		System.out.println(h);

		strongReference = null;
		//JAVA GARBAGE COLLECTION
		System.gc();

		//WeakHashMap OUTPUT AFTER null the strongReference
		//AFTER null THE KEY THE ELEMENT IS REMOVED FROM Map
		System.out.println(h);
	}
}
