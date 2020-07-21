package com.keyword.finals;

/**
 * static variables, non-static variables and local variables all can be final.
 * once the final variables are initialized, even you can’t re-assign same
 * value.
 * 
 * If the global variables are not initialized explicitly, they get default
 * value at the time of object creation. But Final global variables don’t get
 * default value and they must be explicitly initialized at the time of object
 * creation. Uninitialized final field is called Blank Final Field.
 * 
 * final non-static global variable must be initialized at the time of
 * declaration or in all constructors or in any one of IIBs – Instance
 * Initialization Blocks. 
 * 
 * final static global variable must be initialized at
 * the time of declaration or in any one of SIBs – Static Initialization Blocks.
 * (final static global variable can’t be initialized in constructors) .
 * 
 * 
 * @author Administrator
 * 
 */
class B {
	static final int i = 10; // final static variable
	final int j = 20; // final non-static variable

	void methodOne(final int k) {
		// k is final local variable
		// k = 20; //compile time error
	}
}

public class FinalExample {
	public static void main(String[] args) {
		B a = new B();

		// a.i = 10; //Compile time error
		// a.j = 20; //even you can't assign same value to final variables

		a.methodOne(20);
	}
}
