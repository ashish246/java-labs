package com.ds.array;

import java.util.Arrays;

/**
 * There are four ways to copy arrays
 * 
 * 1) using a loop structure
 * 
 * 2) using Arrays.copyOf()
 * 
 * 3) using System.arraycopy()
 * 
 * 4) using clone()
 * 
 * The most efficient copying data between arrays is provided by
 * System.arraycopy() method. The method requires five arguments. Here is its
 * signature
 * 
 * public static void arraycopy(Object source, int srcIndex, Object destination,
 * int destIndex, int length)
 * 
 * Cloning involves creating a new array of the same size and type and copying
 * all the old elements into the new array.
 * 
 * Arrays.copyOf(T[], int) is easier to read. Internaly it uses
 * System.arraycopy() which is a native call.
 * 
 * System.arraycopy() uses JNI (Java Native Interface) to copy an array (or
 * parts of it), so it is blazingly fast
 * 
 * @author Administrator
 * 
 */
public class CopyArrayDemo {

	public static void main(String[] args) {
		int[] obj1 = { 1, 2, 3 };

		// copying by using a loop structure
		int[] obj2 = new int[obj1.length];
		for (int i = 0; i < obj1.length; i++)
			obj2[i] = obj1[i];

		// copying by using arraycopy()
		int[] obj3a = new int[obj1.length];
		System.arraycopy(obj1, 0, obj3a, 0, obj1.length);
		System.out.println(Arrays.toString(obj3a));

		int[] obj3b = new int[obj1.length - 1];
		System.arraycopy(obj1, 1, obj3b, 0, obj1.length - 1);
		System.out.println(Arrays.toString(obj3b));

		// copying by using copyOf()
		int[] obj4 = Arrays.copyOf(obj1, 2);
		System.out.println(Arrays.toString(obj4));

		// copying by using clone()
		int[] obj5 = (int[]) obj1.clone();
		System.out.println(Arrays.toString(obj5));
		
		System.out.println(" X equal to Y: " + Arrays.equals(obj1, obj3a));
		System.out.println(" X equal to Y: " + (obj1 == obj3a));
	}
}
