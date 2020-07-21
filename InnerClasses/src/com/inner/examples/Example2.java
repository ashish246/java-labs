package com.inner.examples;

/**
 * 1) Nested static class doesn’t need reference of Outer class, but Non-static
 * nested class or Inner class requires Outer class reference.
 * 
 * @author Administrator
 * 
 */
public class Example2 {
	class A {
	}

	static class B {
	}

	public static void main(String[] args) {
		/*
		 * will fail - compilation error, you need an instance of Test to
		 * instantiate A
		 */
		// A a = new A();
		/*
		 * will compile successfully, not instance of Test is needed to
		 * instantiate B
		 */
		B b = new B();
	}
}