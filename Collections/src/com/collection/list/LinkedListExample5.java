package com.collection.list;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample5
{
	public static void main(String args[])
	{
		String elements[] = {"Huda Tutorials","Java Tutorials","C Tutorials"};
		String elements2[] = {"Huda Tutorials","Java Tutorials","Android Tutorials"};

		//java.util.List DECLARATION
		List s, s2;

		//java.util.List OBJECT CREATION USING ARRAY AS LIST
		s = new LinkedList(Arrays.asList(elements));
		s2 = new LinkedList(Arrays.asList(elements2));

		//COMPARE TWO COLLECTIONS
		System.out.println("Equals : " + s2.equals(s));

		//CONTAINS COLLECTION IN OTHER COLLECTION
		System.out.println("Contains : " + s2.containsAll(s));
	}
}
