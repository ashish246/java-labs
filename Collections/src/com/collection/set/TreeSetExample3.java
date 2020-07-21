package com.collection.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetExample3
{
	public static void main(String args[])
	{
		String elements[] = {"Huda Tutorials","Java Tutorials","C Tutorials"};
		TreeSet ts; //java.util.TreeSet DECLARATION

		//java.util.Set OBJECT CREATION USING ARRAY AS LIST
		//USING DEFAULT CONSTRUCTOR
		ts = new TreeSet(Arrays.asList(elements));

		//java.util.TreeSet OUTPUT
		Iterator itr = ts.descendingIterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
	}
}
