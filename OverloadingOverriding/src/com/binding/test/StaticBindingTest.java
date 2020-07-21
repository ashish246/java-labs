package com.binding.test;

import java.util.Collection;
import java.util.HashSet;

/**
 * In above example of static binding in Java we have overloaded sort() method,
 * one of which accept Collection and other accept HashSet. we have called
 * sort() method with HashSet as object but referenced with type Collection and
 * when we run method with collection as argument type gets called because it
 * was bonded on compile time based on type of variable (Static binding) which
 * was collection.
 * 
 * 
 * @author Administrator
 * 
 */
public class StaticBindingTest {

	public static void main(String args[]) {
		Collection c = new HashSet();
		StaticBindingTest et = new StaticBindingTest();
		et.sort(c);

	}

	// overloaded method takes Collection argument
	public Collection sort(Collection c) {
		System.out.println("Inside Collection sort method");
		return c;
	}

	// another overloaded method which takes HashSet argument which is sub class
	public Collection sort(HashSet hs) {
		System.out.println("Inside HashSet sort method");
		return hs;
	}

}
