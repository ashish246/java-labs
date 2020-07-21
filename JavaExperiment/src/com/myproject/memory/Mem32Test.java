package com.myproject.memory;

import java.util.LinkedList;
import java.util.List;

/**
 * http://java-performance.info/over-32g-heap-java/
 * 
 * @author Administrator
 * 
 */
public class Mem32Test {

	public static void main(String[] args) {

		List<Integer> lst = new LinkedList<>();
		int i = 0;
		while (true) {
			lst.add(new Integer(i++));
			if ((i & 0xFFFF) == 0)
				System.out.println(i); // shows where you are <img
										// src="http://java-performance.info/wp-includes/images/smilies/icon_smile.gif"
										// alt=":)" class="wp-smiley" />
			if (i == System.currentTimeMillis())
				break; // otherwise will not compile
		}
		System.out.println(lst.size()); // needed to avoid dead code
										// optimizations
	}
}