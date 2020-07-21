package com.keyword.statics;

/**
 * Java has no way of making a top-level class static but you can simulate a
 * static class like this:
 * 
 * Declare your class final - Prevents extension of the class since extending a
 * static class makes no sense
 * 
 * Make the constructor private - Prevents instantiation by client code as it
 * makes no sense to instantiate a static class
 * 
 * Make all the members and functions of the class static - Since the class
 * cannot be instantiated no instance methods can be called or instance fields
 * accessed
 * 
 * Note that the compiler will not prevent you from declaring an instance
 * (non-static) member. The issue will only show up if you attempt to call the
 * instance member
 * 
 * @author Administrator
 * 
 */
public class TestMyStaticClass {
	public static void main(String[] args) {
		MyStaticClass.setMyStaticMember(5);
		System.out
				.println("Static value: " + MyStaticClass.getMyStaticMember());
		System.out.println("Value squared: "
				+ MyStaticClass.squareMyStaticMember());
		// MyStaticClass x = new MyStaticClass(); // results in compile time
		// error
	}
}

// A top-level Java class mimicking static class behavior
final class MyStaticClass {
	private MyStaticClass() { // private constructor
		myStaticMember = 1;
	}

	private static int myStaticMember;

	public static void setMyStaticMember(int val) {
		myStaticMember = val;
	}

	public static int getMyStaticMember() {
		return myStaticMember;
	}

	public static int squareMyStaticMember() {
		return myStaticMember * myStaticMember;
	}
}
