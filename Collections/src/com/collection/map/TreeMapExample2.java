package com.collection.map;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapExample2
{
	public static void main(String args[])
	{
		//java.util.TreeMap DECLARATION
		TreeMap <String,Integer> h;

		//java.util.TreeMap OBJECT CREATION
		//USING DEFAULT CONSTRUCTOR
		h = new TreeMap <String,Integer>();

		//ADD KEY AND VALUE
		h.put("ONE", new Integer(1));
		h.put("TWO", new Integer(2));
		h.put("THREE", new Integer(3));

		//ALLOW null VALUE ONLY
		h.put("FOUR",null);

		//TreeMap KEYS OUTPUT
		Set s = h.entrySet();
		Iterator itr = s.iterator();
		int i=1;
		while(itr.hasNext())
		{
			//Map.Entry IS INNER INTERFACE OF Map INTERFACE
			Map.Entry entry = (Map.Entry) itr.next();
			System.out.println(entry.getKey()+" "+entry.getValue());
		}

		//TreeMap VALUES OUTPUT
		Collection c = h.values();
		Iterator values = c.iterator();
		int j=1;
		while(values.hasNext())
		{
			System.out.println("Value " + j++ + " : " + values.next());
		}
	}
}
