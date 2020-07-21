package com.generics.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * wild card ? can not accept object of any type as they are of unknown type.
 * 
 * Unbounded Wildcards • It is useful if you are writing a method that can be
 * implemented using functionality provided in the Object class. • It is useful
 * when the code is using methods in the generic class that don’t depend on the
 * type parameter (e.g., List.size or List.clear). In fact, Class<?> is often
 * used because most of the methods in Class<T> do not depend on T.
 * 
 * 
 * @author Administrator
 * 
 */
public class GenericTest {

	public static void main(String[] args) {

		Collection<?> c = new ArrayList<String>();
		c.add(new String()); // Compile time error

	}
}

class Experiment {
	public static <E> void funct1(final List<E> list1, final E something) {
		list1.add(something);
	}

	public static <E> void funct4(final List<?> list1, final E something) {
		list1.add(something);
	}

	/**
	 * Since we don't know what the element type of c stands for, we cannot add
	 * objects to it. The add() method takes arguments of type E, the element
	 * type of the collection. When the actual type parameter is ?, it stands
	 * for some unknown type. Any parameter we pass to add would have to be a
	 * subtype of this unknown type. Since we don't know what type that is, we
	 * cannot pass anything in. The sole exception is null, which is a member of
	 * every type.
	 * 
	 * @param list
	 * @param something
	 */
	public static void funct2(final List<?> list, final Object something) {
		list.add(something); // does not compile
		list.add(null);
	}

	public static void funct3(final List list, final Object something) {
		list.add(something); // does not compile
	}
}

class Experiment1 {
	public static <E> void funct1(final List<E> list) {
		list.add(list.get(0));
	}

	public static void funct2(final List<?> list) {
		list.add(list.get(0)); // !!!!!!!!!!!!!! won't compile !!!!!!!!!
	}

	public static void funct3(final List list) {
		list.add(list.get(0)); // !!!!!!!!!!!!!! won't compile !!!!!!!!!
	}
}