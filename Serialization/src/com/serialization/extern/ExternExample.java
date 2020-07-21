package com.serialization.extern;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @author Administrator
 *
 */
public class ExternExample {

	public static void main(String args[]) {

		// create a Car object
		Car car = new Car("Mitsubishi", 2009);
		Car newCar = null;

		// serialize the car
		try {
			FileOutputStream fo = new FileOutputStream("tmp");
			ObjectOutputStream so = new ObjectOutputStream(fo);
			so.writeObject(car);
			so.flush();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}

		// de-serialize the Car
		try {
			FileInputStream fi = new FileInputStream("tmp");
			ObjectInputStream si = new ObjectInputStream(fi);
			newCar = (Car) si.readObject();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}

		/*
		 * Print out the original and new car information
		 */
		System.out.println("The original car is ");
		System.out.println(car);
		System.out.println("The new car is ");
		System.out.println(newCar);
	}
}

class Car implements Externalizable {

	String name;
	int year;

	/*
	 * mandatory public no-arg constructor
	 */
	public Car() {
		super();
	}

	Car(String n, int y) {
		name = n;
		year = y;
	}

	/**
	 * Mandatory writeExernal method.
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);
		out.writeInt(year);
	}

	/**
	 * Mandatory readExternal method.
	 */
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		name = (String) in.readObject();
		year = in.readInt();
	}

	/**
	 * Prints out the fields. used for testing!
	 */
	public String toString() {
		return ("Name: " + name + "\n" + "Year: " + year);
	}
}
