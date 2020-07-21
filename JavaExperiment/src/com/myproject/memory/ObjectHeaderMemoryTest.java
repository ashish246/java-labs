package com.myproject.memory;

/**
 * http://stackoverflow.com/questions/25332563/why-compressed-oops-gives-12-
 * bytes-for-object-header
 * 
 * - Tested the object layout using JOL (Java Object Layout).
 * 
 * @author Administrator
 * 
 */
public class ObjectHeaderMemoryTest {
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));
		//System.out.println(VMSupport.vmDetails());
		//System.out.println(ClassLayout.parseClass(A.class).toPrintable());
	}
}

class A {
	int a;
}