package com.overloading.overriding.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 1) Don't overload method which accept same number of parameter with similar
 * types To surprise of some programmers method with argument type List is
 * called both the time, instead of expected method which takes ArrayList and
 * LinkedList, because method overloading is resolved at compile time using
 * static binding in Java.
 * 
 * 
 * 3) Beware of Autoboxing while overloading method in Java Prior to
 * introduction of Autoboxing and unboxing in Java 5, method which accept
 * primitive type and object type were radically different and it’s clear which
 * method will be invoked. Now with autoboxing it's really confusing. Clasical
 * example of this kind overloading mistake is ArrayList’s remove() method,
 * which is overloaded to accept index as well as Object. when you store Integer
 * in ArrayList and call remove() method, It’s hard to find out which remove()
 * method will be called
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class OverloadingTest {

	public static void main(String args[]) {
		List abc = new ArrayList();
		List bcd = new LinkedList();

		ConfusingOverloading co = new ConfusingOverloading();
		co.hasDuplicates(abc); // should call to ArryList overloaded method
		co.hasDuplicates(bcd); // should call to LinkedList overloaded method

		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		System.out.println("numbers: " + numbers);
		numbers.remove(1); // should remove "1" as element or 2nd element from
							// ArrayList
		System.out.println("numbers: " + numbers);

	}

}

class ConfusingOverloading {

	public boolean hasDuplicates(List collection) {
		System.out.println("overloaded method with Type List ");
		return true;
	}

	public boolean hasDuplicates(ArrayList collection) {
		System.out.println("overloaded method with Type ArrayList ");
		return true;
	}

	public boolean hasDuplicates(LinkedList collection) {
		System.out.println("overloaded method with Type LinkedList ");
		return true;
	}

}
