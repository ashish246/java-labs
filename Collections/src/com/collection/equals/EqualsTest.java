package com.collection.equals;

import java.util.HashSet;
import java.util.Set;

/**
 * equals() method, as name suggest, is used to simply verify the equality of
 * two objects. Default implementation simply check the object references of two
 * objects to verify their equality.
 * 
 * If both employee objects have been equal, in a Set which stores only unique
 * objects, there must be only one instance inside HashSet, after all both
 * objects refer to same employee.
 * 
 * As java docs say, if you override equals() method then you must override
 * hashCode() method.
 * 
 * hashCode() method is used to get a unique integer for given object. This
 * integer is used for determining the bucket location, when this object needs
 * to be stored in some HashTable like data structure. By default, Object’s
 * hashCode() method returns and integer representation of memory address where
 * object is stored.
 * 
 * @author Administrator
 * 
 */
public class EqualsTest {
	public static void main(String[] args) {
		Employee e1 = new Employee();
		Employee e2 = new Employee();

		e1.setId(100);
		e2.setId(100);

		// Prints false if equals() is not overridden in Employee
		System.out.println(e1.equals(e2));

		Set<Employee> employees = new HashSet<Employee>();
		employees.add(e1);
		employees.add(e2);

		// Prints two objects (even if the objects are equal, i.e. equals() is
		// overridden) if hashcode() is
		// not overridden in Employee
		System.out.println(employees);
	}
}
