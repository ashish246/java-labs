package com.collection.list;

import java.util.LinkedList;

public class LinkedListExample9
{
	public static void main(String args[])
	{
		//java.util.LinkedList DECLARATION
		LinkedList al;

		//java.util.LinkedList OBJECT CREATION
		//USING DEFAULT CONSTRUCTOR
		al = new LinkedList();

		//ADD AN ELEMENT
		al.addFirst("Java Tutorials");

		//ADD AN ELEMENT AT LAST
		al.addLast("C Tutorials");
		al.addLast("CPP Tutorials");

		//ADD AN ELEMENT AT FIRST
		al.addFirst("Huda Tutorials2");

		//ADD AN ELEMENT
		al.push("Huda Tutorials");

		//java.util.LinkedList OUTPUT
		System.out.println(al);

		//GET THE FIRST ELEMENT
		System.out.println("First Element : " + al.getFirst());

		//GET THE LAST ELEMENT
		System.out.println("Last Element : " + al.getLast());

		//GET THE HEAD (FIRST) ELEMENT
		System.out.println("Head (First) Element : " + al.peek());

	}
}
