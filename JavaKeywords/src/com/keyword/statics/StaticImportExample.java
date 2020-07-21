package com.keyword.statics;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
//import static java.lang.Long.MIN_VALUE;
import static java.lang.Long.*;

/**
 * 2) If you import two static fields with same name explicitly e.g.
 * Integer.MAX_VALUE and Long.MAX_VALUE then Java will throw compile time error.
 * But if other static modifier is not imported explicitly e.g. you have
 * imported java.lang.Long.*, MAX_VALUE will refer to Integer.MAX_VALUE.
 * 
 * 3) You can apply static import statement not only on static fields but also
 * on static methods in Java.
 * 
 * 
 * @author Administrator
 * 
 */
public class StaticImportExample {

	public static void main(String args[]) {

		// without Static import
		System.out.println("Maximum value of int variable in Java without "
				+ "static import : " + Integer.MAX_VALUE);
		System.out
				.println("Minimum value of int variable in Java without static import : "
						+ Integer.MIN_VALUE);

		// after static import in Java 5
		System.out
				.println("Maximum value of int variable using static import : "
						+ MAX_VALUE);
		System.out
				.println("Minimum value of int variable using static import : "
						+ MIN_VALUE);
	}
}
