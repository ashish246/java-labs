package com.collection.map;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakHashMapExample2
{
	public static void main(String args[])
	{
		//java.util.WeakHashMap DECLARATION
		WeakHashMap <String,Integer> h;

		//java.util.WeakHashMap OBJECT CREATION
		//USING DEFAULT CONSTRUCTOR
		h = new WeakHashMap <String,Integer>();

		//ADD KEY AND VALUE
		h.put("ONE", new Integer(1));
		h.put("TWO", new Integer(2));
		h.put("THREE", new Integer(3));

		//ALLOW null KEY AND VALUE
		h.put(null,null);

		//WeakHashMap KEYS OUTPUT
		Set s = h.keySet();
		Iterator itr = s.iterator();
		int i=1;
		while(itr.hasNext())
		{
			System.out.println("Key " + i++ + " : " + itr.next());
		}

		//WeakHashMap VALUES OUTPUT
		Collection c = h.values();
		Iterator values = c.iterator();
		int j=1;
		while(values.hasNext())
		{
			System.out.println("Value " + j++ + " : " + values.next());
		}
	}
}
