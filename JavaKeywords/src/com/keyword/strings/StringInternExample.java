package com.keyword.strings;

/**
 * String intern or simply intern refers to string object in the String Constant
 * Pool. Interning is the process of creating a string object in String Constant
 * Pool which will be exact copy of string object in heap memory.
 * 
 * @author Administrator
 * 
 */
public class StringInternExample {

	public static void main(String[] args) {
		String s1 = new String("JAVA");

		String s2 = s1.intern(); // Creating String Intern

		System.out.println(s1 == s2); // Output : false

		System.out.println(s1.equals(s2)); // Output : true
	}

}
