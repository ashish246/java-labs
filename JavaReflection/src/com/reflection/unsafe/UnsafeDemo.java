package com.reflection.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * Java is a safe programming language and prevents programmer from doing a lot
 * of stupid mistakes, most of which were based on memory management. But, if
 * you are determined to mess with it, you have Unsafe class. This class is a
 * sun.* API which isn’t really part of the J2SE, so you may not find any
 * official documentation. Sadly,It also does not have any good code
 * documentation too.
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class UnsafeDemo {
	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal
																// reference
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);

		// This creates an instance of player class without any initialization
		Player p = (Player) unsafe.allocateInstance(Player.class);
		System.out.println(p.getAge()); // Print 0

		p.setAge(45); // Let's now set age 45 to un-initialized object
		System.out.println(p.getAge()); // Print 45

		System.out.println(new Player().getAge()); // This the normal way to get
													// fully initialized object;
													// Prints 50
	}
}

class Player {
	private int age = 12;

	public Player() { // Even if you create this constructor private;
						// You can initialize using Unsafe.allocateInstance()
		this.age = 50;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
