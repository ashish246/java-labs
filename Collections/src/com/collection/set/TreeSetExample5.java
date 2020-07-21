package com.collection.set;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample5
{
	public static void main(String args[])
	{
		String elements[] = {"Huda Tutorials","Java Tutorials","C Tutorials"};
		String elements2[] = {"Huda Tutorials","Java Tutorials","Android Tutorials"};

		//java.util.TreeSet DECLARATION
		Set s, s2;

		//java.util.Set OBJECT CREATION USING ARRAY AS LIST
		//USING DEFAULT CONSTRUCTOR
		s = new TreeSet(Arrays.asList(elements));
		s2 = new TreeSet(Arrays.asList(elements2));

		//COMPARE TWO COLLECTIONS
		System.out.println("Equals : " + s2.equals(s));

		//CONTAINS COLLECTION IN OTHER COLLECTION
		System.out.println("Contains : " + s2.containsAll(s));
	}
}
