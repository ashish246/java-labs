package com.inner.examples;

/* Java program to demonstrate how to implement static and non-static
 classes in a java program. */
class OuterClass0 {
	private static String msg = "GeeksForGeeks";

	// Static nested class
	public static class NestedStaticClass {

		// Only static members of Outer class is directly accessible in nested
		// static class
		public void printMessage() {

			// Try making 'message' a non-static variable, there will be
			// compiler error
			System.out.println("Message from nested static class: " + msg);
		}
	}

	// non-static nested class - also called Inner class
	public class InnerClass {

		// Both static and non-static members of Outer class are accessible in
		// this Inner class
		public void display() {
			System.out.println("Message from non-static nested class: " + msg);
		}
	}
}

/**
 * @author Administrator
 * 
 */
public class Example1 {
	// How to create instance of static and non static nested class?
	public static void main(String args[]) {

		// create instance of nested Static class
		OuterClass0.NestedStaticClass printer = new OuterClass0.NestedStaticClass();

		// call non static method of nested static class
		printer.printMessage();

		// In order to create instance of Inner class we need an Outer class
		// instance. Let us create Outer class instance for creating
		// non-static nested class
		OuterClass0 outer = new OuterClass0();
		OuterClass0.InnerClass inner = outer.new InnerClass();

		// calling non-static method of Inner class
		inner.display();

		// we can also combine above steps in one step to create instance of
		// Inner class
		OuterClass0.InnerClass innerObject = new OuterClass0().new InnerClass();

		// similarly we can now call Inner class method
		innerObject.display();
	}
}