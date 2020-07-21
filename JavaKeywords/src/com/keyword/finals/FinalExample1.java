package com.keyword.finals;

/**
 * When an array reference variable is declared as final, only variable itself
 * is final but not the array elements.
 * 
 * When an reference variable is declared as final, you can’t re-assign a new
 * object to it once it is referring to an object. But, you can change the state
 * of an object to which final reference variable is referring.
 * 
 * @author Administrator
 * 
 */
public class FinalExample1 {
	public static void main(String[] args) {
		final int X[] = new int[10]; // final array variable

		X[2] = 10;
		X[2] = 20; // Array element can be re-assigned

		// X = new int[30]; //compile time error
		// can't re-assign new array object to final array variable
		
		final A a = new A();  //final reference variable
		 
        a.i = 50;
        //you can change the state of an object to which final reference variable is pointing
 
       // a = new A();  //compile time error
 
        //you can't re-assign a new object to final reference variable
	}
}

class A
{
    int i = 10;
}
