package com.inner.examples;

/**
 * @author Administrator
 * 
 */
public class ExampleInheritance1 {
	public static void main(String args[]) {
		OuterClass1 outer = new OuterClass1(); // Instantiating OuterClass

		OuterClass1.InnerClassTwo innerTwo = outer.new InnerClassTwo(); // Instantiating
																		// InnerClassTwo

		System.out.println(innerTwo.x); // Accessing inherited field x from
										// InnerClassOne
		innerTwo.methodOfInnerClassOne(); // calling inherited method from
											// InnerClassOne
	}
}

class OuterClass1 {
	class InnerClassOne {
		int x = 10;

		void methodOfInnerClassOne() {
			System.out.println("From InnerClassOne");
		}
	}

	class InnerClassTwo extends InnerClassOne {
		// One Inner Class can extend another inner class
	}
}
