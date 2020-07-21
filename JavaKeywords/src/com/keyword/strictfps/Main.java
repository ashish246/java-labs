package com.keyword.strictfps;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * @author Administrator
 *
 */
public class Main {
	
	public static void main(String... args) throws Exception {
		
		Class<?> c = Class.forName("java.lang.String");
		Constructor[] allConstructors = c.getDeclaredConstructors();
		
		for (Constructor ctor : allConstructors) {
			int searchMod = Modifier.STRICT;
			int mods = accessModifiers(ctor.getModifiers());
			if (searchMod == mods) {
				System.out.println(ctor);
			}
		}
	}

	private static int accessModifiers(int m) {
		return m & Modifier.STRICT;
	}
}
