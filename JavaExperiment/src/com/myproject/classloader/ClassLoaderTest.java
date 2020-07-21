package com.myproject.classloader;

public class ClassLoaderTest {

	public static void main(String[] args) {

		ClassLoader classLoader = MainClass.class.getClassLoader();

		try {
			Class aClass = classLoader.loadClass("com.myproject.classloader.MainClass");
			System.out.println("aClass.getName() = " + aClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

