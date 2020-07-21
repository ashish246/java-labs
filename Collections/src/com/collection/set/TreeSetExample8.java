package com.collection.set;

import java.util.Collections;
import java.util.TreeSet;

public class TreeSetExample8
{
	public static void main(String args[])
	{
		//java.util.TreeSet DECLARATION
		TreeSet ts;

		//java.util.TreeSet OBJECT CREATION
		//USING DEFAULT CONSTRUCTOR
		ts = new TreeSet(Collections.reverseOrder());

		//ADD AN ELEMENT
		ts.add("Huda Tutorials");
		ts.add("Java Tutorials");
		ts.add("C Tutorials");
		ts.add("CPP Tutorials");

		//java.util.TreeSet OUTPUT
		System.out.println(ts);

		//java.util.TreeSet Comparator
		System.out.println(ts.comparator());
	}
}
                    
