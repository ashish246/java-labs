package com.inner.examples;

/**
 * If an inner class declares fields or methods with the same names as field or
 * methods in its enclosing class, the inner fields or methods are said to
 * shadow over the outer fields or methods.
 * 
 * @author Administrator
 * 
 */
public class Example3 {

	private String text = "I am Outer private!";

	public class Inner {

		private String text = "I am Inner private";

		public void printText() {
			System.out.println(text);
			System.out.println(Example3.this.text);
		}
	}

	public static void main(String[] args) {

		Example3.Inner inner = new Example3().new Inner();
		inner.printText();
	}
}
