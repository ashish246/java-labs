package com.ds.array;

import java.util.Arrays;
import java.util.Collections;

public class ArraysExample3
{
	public static void main(String args[])
	{
		//String ARRAY DECLARATION AND CREATION
		String arr[] = {"Java Tutorials","NetBeans Tutorials","Huda Tutorials","C"};

		//String ARRAY OUTPUT AS STRING
		for(int i=0;i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}

		//String ARRAY SORTING
		Arrays.sort(arr);

		//String ARRAY OUTPUT AS STRING AFTER SORTING
		System.out.println("\nAfter Sorting\n");
		for(int i=0;i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}

		//SEARCHING IN String ARRAY
		int index = Arrays.binarySearch(arr, "Huda Tutorials");
		if( index >= 0 )
			System.out.println("\nHuda Tutorials is Found at Index : " + index);
		else
			System.out.println("\nHuda Tutorials is NOT Found");

		//String ARRAY REVERSE ORDER
		Arrays.sort(arr,Collections.reverseOrder());

		//String ARRAY OUTPUT AS STRING AFTER SORTING
		System.out.println("\nAfter Reverse Order\n");
		for(int i=0;i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}

		//FILLING String ARRAY WITH DEFAULT null
		Arrays.fill(arr,null);

		//String ARRAY OUTPUT AFTER FILL
		System.out.println(Arrays.toString(arr));
	}
}
