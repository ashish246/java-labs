package com.ds.array;

import java.util.Arrays;

/**
 * The assignment operator creates an alias to the object.
 * 
 * @author Administrator
 * 
 */
public class ArrayTest {

	public static void main(String[] args) {

		int[] a = { 9, 5, 4 };
		int[] b = a;

		System.out.println(" a equal to b: " + (a == b));

		String[] X = { new String("1"), new String("2"), new String("3") };
		String[] Y = X;

		System.out.println(" X equal to Y: " + (X == Y));
		System.out.println(" X equal to Y: " + Arrays.equals(X, Y));
		System.out.println(" X equal to Y: " + Arrays.toString(X));

		int i = 132;
		int j = i;

		System.out.println(" i equal to j: " + (i == j));
	}

}
