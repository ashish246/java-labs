package com.binding.test;

/**
 * In this example of Dynamic Binding we have used concept of method overriding.
 * Car extends Vehicle and overrides its start() method and when we call start()
 * method from a reference variable of type Vehicle, it doesn't call start()
 * method from Vehicle class instead it calls start() method from Car subclass
 * because object referenced by Vehicle type is a Car object. This resolution
 * happens only at runtime because object only created during runtime and called
 * dynamic binding in Java. Dynamic binding is slower than static binding because
 * it occurs in runtime and spends some time to find out actual method to be
 * called.
 * 
 * 
 * @author Administrator
 * 
 */
public class DynamicBindingTest {

	public static void main(String args[]) {
		Vehicle vehicle = new Car(); // here Type is vehicle but object will be
										// Car
		vehicle.start(); // Car's start called because start() is overridden
							// method
	}
}

class Vehicle {

	public void start() {
		System.out.println("Inside start method of Vehicle");
	}
}

class Car extends Vehicle {

	@Override
	public void start() {
		System.out.println("Inside start method of Car");
	}
}
