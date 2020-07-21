package com.collection.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample3
{
	public static void main(String args[])
	{
		String elements[] = {"Huda Tutorials","Java Tutorials","C Tutorials"};
		List s; //java.util.List DECLARATION

		//java.util.List OBJECT CREATION USING ARRAY AS LIST
		s = new LinkedList(Arrays.asList(elements));

		//java.util.List OUTPUT
		Iterator itr = s.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
	}
}
