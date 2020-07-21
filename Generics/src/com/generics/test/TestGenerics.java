package com.generics.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The buzzing keyword is "Type Erasure", you guessed it right it’s the same
 * thing we used to in our schools for erasing our mistakes in writing or
 * drawing :). Same thing is done by Java compiler, when it sees code written
 * using Generics it completely erases that code and covert it into raw type
 * i.e. code without Generics. All type related information is removed during
 * erasing. So your ArrayList<Gold> becomes plain old ArrayList prior to JDK
 * 1.5, formal type parameters e.g. <K, V> or <E> gets replaced by either Object
 * or Super Class of the Type. Also when the translated code is not type correct
 * compiler inserts a type casting operator. This all done behind the scene so
 * you don't need to worry about what important to us is that Java compiler
 * guarantees type-safety and flag any type-safety relate error during
 * compilation. In short Generics in Java is syntactic sugar and doesn’t store
 * any type related information at runtime. All type related information is
 * erased by Type Erasure, this was the main requirement while developing
 * Generics feature in order to reuse all Java code written without Generics.
 * 
 * 
 * @author Administrator
 * 
 */
public class TestGenerics {

	public static void main(String[] args) {

		// Parametrized type like Set<T> is subtype of raw type Set and you can
		// assign Set<T> to Set, following code is legal in Java:
		Set setOfRawType = new HashSet<String>();
		setOfRawType = new HashSet<Integer>();

		// Set<Object> is setOfAnyType, it can store String, Integer but you can
		// not assign setOfString or setOfInteger to setOfObject using Generics
		// in Java.
		Set<Object> setOfAnyType = new HashSet<Object>();
		setOfAnyType.add("abc"); // legal
		setOfAnyType.add(new Float(3.0f)); // legal - <Object> can accept any
											// type

		// Set<?> represents SetOfUnknownType and you can assign SetOfString or
		// SetOfInteger to Set<?> as shown in below example of Generics :
		Set<?> setOfUnknownType = new LinkedHashSet<String>();
		setOfUnknownType = new LinkedHashSet<Integer>();

		// Parametrized Type also follow Inheritance at main Type level means
		// both HashSet<String> and LinkedHashSet<String> are sub types of
		// Set<String> and legal by compiler as shown in following Generics
		// example in Java :
		Set<String> setOfString = new HashSet<String>(); // valid in Generics
		setOfString = new LinkedHashSet<String>(); // Ok

		// But Inheritance on type parameter is not supported means Set<Object>
		// will not accept Set<String> as per following Generics code.
		Set<Object> SetOfObject = new HashSet<String>(); // compiler error -
															// incompatible type

		// Set<? extends Number> will store either Number or sub type of Number
		// like Integer, Float. This is an example of bounded wildcards in
		// Generics
		Set<? extends Number> setOfAllSubTypeOfNumber = new HashSet<Integer>(); // legal
																				// -
																				// Integer
																				// extends
																				// Number
		setOfAllSubTypeOfNumber = new HashSet<Float>(); // legal - because Float
														// extends Number

		// Set<? super TreeMap> is another example of bounded wildcards, which
		// will store instances of TreeMap or super class of TreeMap. See
		// following Generics example in Java :
		Set<? super TreeMap> setOfAllSuperTypeOfTreeMap = new LinkedHashSet<TreeMap>(); // legal
																						// because
																						// TreeMap
																						// is
																						// superType
																						// of
																						// itself

		setOfAllSuperTypeOfTreeMap = new HashSet<SortedMap>(); // legal because
																// SorteMap is
																// super class
																// of TreeMap
		setOfAllSuperTypeOfTreeMap = new LinkedHashSet<Map>(); // legal since
																// Map is super
																// type of
																// TreeMap

		// You can not use Generics in .class token, parametrized types like
		// List<String> are not allow along with .class literal. This is the one
		// place where you need to use Raw type instead of parametrized type in
		// Java.
		Class tClass = List.class; // legal
		// List<String>.class //illegal

		// ype inference : Generics in Java does not support type inference
		// while calling constructor or creating instance of Generic Types until
		// JDK7, In Java 7 along with Automatic resource management and String
		// in Switch also added a new operator called Diamond operator and
		// denoted by <> which facilitate type inference while creating instance
		// of Generics classes. this helps to reduce redundancy and clutter.
		// here is an example of Diamond operator in Java7 code:

		// prior to JDK 7
		HashMap<String, Set<Integer>> contacts = new HashMap<String, Set<Integer>>();

		// JDK 7 diamond operator
		HashMap<String, Set<Integer>> contacts1 = new HashMap<>();

		// "?" denotes any unknown type, It can represent any Type at in code
		// for. Use this wild card if you are not sure about Type. for example
		// if you want to have a ArrayList which can work with any type than
		// declare it as "ArrayList<?> unknownList"

		ArrayList<?> unknownList = new ArrayList<>();
		unknownList = new ArrayList<Float>();

		
	}

	// If you are writing Generics method than you need to declare type
	// parameters in method signature between method modifiers and return
	// type as shown in below Java Generics example :
	public static <T> T identical(T source) {
		return source;
	}

}
