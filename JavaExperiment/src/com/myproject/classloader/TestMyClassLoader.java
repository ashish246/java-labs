package com.myproject.classloader;

/**
 * 
 * @author Administrator
 * 
 */
public class TestMyClassLoader {

	public static void main(String[] args) throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {

		ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
		MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
		Class myObjectClass = classLoader
				.loadClass("com.myproject.classloader.MyObject");

		MyObjectInterface object1 = (MyObjectInterface) myObjectClass
				.newInstance();

		System.out.println("Loaded by 1st class loader");

		/*
		 * MyObjectSuperClass object2 = (MyObjectSuperClass)
		 * myObjectClass.newInstance();
		 */

		// create new class loader so classes can be reloaded.
		classLoader = new MyClassLoader(parentClassLoader);
		myObjectClass = classLoader
				.loadClass("com.myproject.classloader.MyObject");

		object1 = (MyObjectInterface) myObjectClass.newInstance();
		// object2 = (MyObjectSuperClass) myObjectClass.newInstance();

		System.out.println("Loaded by 2nd class loader");

	}

}
