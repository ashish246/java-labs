package com.keyword.strings;

/**
 * 1). + is internally converted into below code by the compiler if the string
 * reference is not final.
 * 
 * 2). concat() method.
 * 
 * public String concat(String str) { int otherLen = str.length(); if (otherLen
 * == 0) { return this; } char buf[] = new char[count + otherLen]; getChars(0,
 * count, buf, 0); // Uses system.arraycopy() str.getChars(0, otherLen, buf,
 * count); // Uses system.arraycopy() return new String(0, count + otherLen,
 * buf); }
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class StringConcatenatePerf {

	public static void main(String args[]) {

		String s2 = "aa";
		// for (int x = 0; x < 20; ++x) {
		// Using the + operator
		String s3 = "";

		long startTime = System.currentTimeMillis();
		for (int t = 0; t < 100000; ++t) {
			s3 = s3 + s2;
		}
		long endTime = System.currentTimeMillis();

		System.out.println("time taken for + operator: "
				+ (endTime - startTime));

		// Using String.concat()
		s3 = "";
		startTime = System.currentTimeMillis();
		for (int t = 0; t < 100000; ++t) {
			s3 = s3.concat(s2);
		}
		endTime = System.currentTimeMillis();

		System.out.println("time taken for concat() operator: "
				+ (endTime - startTime));

		// using StringBUffer
		StringBuffer sbf = new StringBuffer("JAVA");

		startTime = System.currentTimeMillis();

		for (int i = 0; i <= 10000; i++) {
			sbf.append("J2EE");
		}

		endTime = System.currentTimeMillis();

		System.out.println("Time taken by StringBuffer class : "
				+ (endTime - startTime) + " ms");

		// Using StringBuilder
		s3 = "";
		startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder(s3);
		for (int t = 0; t < 100000; ++t) {
			sb.append(s2);
		}
		endTime = System.currentTimeMillis();

		System.out.println("time taken for StringBuilder operator: "
				+ (endTime - startTime));

		s3 = sb.toString();
		// }

	}

}
