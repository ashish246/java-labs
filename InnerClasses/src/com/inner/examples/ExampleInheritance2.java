package com.inner.examples;

/**
 * An inner class can be extended by another class outside of it’s outer class.
 * If you are extending static inner class (Static nested class), then it is a
 * straight forward implementation. If you are extending non-static inner class,
 * then sub class constructor must explicitly call super class constructor using
 * an instance of outer class. Because, you can’t access non-static inner class
 * without the instance of outer class. 
 * 
 * @author Administrator
 * 
 */
public class ExampleInheritance2 {

}

class OuterClass2 {
	static class InnerClassOne {
		// Class as a static member
	}

	class InnerClassTwo {
		// Class as a non-static member
	}
}

// Extending Static inner class or static nested class
class AnotherClassOne extends OuterClass2.InnerClassOne {
	// static nested class can be referred by outer class name,
}

// Extending non-static inner class or member inner class
class AnotherClassTwo extends OuterClass2.InnerClassTwo {
	public AnotherClassTwo() {
		new OuterClass2().super(); // accessing super class constructor through
									// OuterClass instance
	}
}
