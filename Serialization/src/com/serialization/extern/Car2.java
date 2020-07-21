package com.serialization.extern;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The superclass implements externalizable
 */
class Automobile2 implements Externalizable {

	/*
	 * Instead of making thse members private and adding setter and getter
	 * methods, I am just giving default access specifier. You can make them
	 * private members and add setters and getters.
	 */
	String regNo;
	String mileage;

	/*
	 * A public no-arg constructor
	 */
	public Automobile2() {
	}

	Automobile2(String rn, String m) {
		regNo = rn;
		mileage = m;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(regNo);
		out.writeObject(mileage);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		regNo = (String) in.readObject();
		mileage = (String) in.readObject();
	}

}

public class Car2 extends Automobile2 implements Externalizable {

	String name;
	int year;

	/*
	 * mandatory public no-arg constructor
	 */
	public Car2() {
		super();
	}

	Car2(String n, int y) {
		name = n;
		year = y;
	}

	/**
	 * Mandatory writeExernal method.
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		// first we call the writeExternal of the superclass as to write
		// all the superclass data fields
		super.writeExternal(out);

		// Now the subclass fields
		out.writeObject(name);
		out.writeInt(year);
	}

	/**
	 * Mandatory readExternal method.
	 */
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// first call the superclass external method
		super.readExternal(in);

		// Now the subclass fields
		name = (String) in.readObject();
		year = in.readInt();
	}

	/**
	 * Prints out the fields. used for testing!
	 */
	public String toString() {
		return ("Reg No: " + regNo + "\n" + "Mileage: " + mileage + "Name: "
				+ name + "\n" + "Year: " + year);
	}
}
