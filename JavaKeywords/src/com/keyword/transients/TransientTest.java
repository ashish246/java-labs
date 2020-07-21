package com.keyword.transients;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * transient variable in Java is a variable whose value is not serialized during
 * Serialization and which is initialized by its default value during
 * de-serialization, for example for object transient variable it would be null.
 * this behavior can be customized by using Custom Serialized form or by using
 * Externalizable interface. transient variable is used to prevent any object
 * from being serialized and you can make any variable transient by using
 * transient keyword.
 * 
 * @author Administrator
 * 
 */
public class TransientTest {

	public static void main(String args[]) {

		Book narnia = new Book(1024, "Narnia", "unknown", 2);
		System.out.println("Before Serialization: " + narnia);

		try {
			FileOutputStream fos = new FileOutputStream("narnia.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(narnia);

			System.out.println("Book is successfully Serialized ");

			FileInputStream fis = new FileInputStream("narnia.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Book oldNarnia = (Book) ois.readObject();

			System.out
					.println("Book successfully created from Serialized data");
			System.out.println("Book after seriazliation : " + oldNarnia);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/*
 * A class which implements Serializable interface and has a transient variable.
 */
class Book implements Serializable {
	private int ISBN;
	private String title;
	private String author;
	private transient int edition = 1; // transient variable not serialized

	public Book(int ISBN, String title, String author, int edition) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "Book{" + "ISBN=" + ISBN + ", title=" + title + ", author="
				+ author + ", edition=" + edition + '}';
	}

}
