package com.collection.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample3
{
	public static void main(String args[])
	{
		String elements[] = {"Huda Tutorials","Java Tutorials","C Tutorials"};
		Set s; //java.util.Set DECLARATION

		//java.util.Set OBJECT CREATION USING ARRAY AS LIST
		s = new HashSet(Arrays.asList(elements));

		//java.util.Set OUTPUT
		Iterator itr = s.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
	}
}
