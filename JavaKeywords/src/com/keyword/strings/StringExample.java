package com.keyword.strings;

/**
 * One more interesting thing about String Constant Pool is that, pool space is
 * allocated to an object depending upon it’s content. There will be no two
 * objects in the pool having the same content. This is what happens when you
 * create string objects using string literal, “When you create a string object
 * using string literal, JVM first checks the content of to be created object.
 * If there exist an object in the pool with the same content, then it returns
 * the reference of that object. It doesn’t create new object. If the content is
 * different from the existing objects then only it creates new object.” But,
 * when you create string objects using new keyword, a new object is created
 * whether the content is same or not.
 * 
 * @author Administrator
 * 
 */
public class StringExample {
	public static void main(String[] args) {
		// Creating string objects using literals

		String s1 = "abc";

		String s2 = "abc";

		System.out.println(s1 == s2); // Output : true

		// Creating string objects using new operator

		String s3 = new String("abc");

		String s4 = new String("abc");

		System.out.println(s3 == s4); // Output : false
	}
}
