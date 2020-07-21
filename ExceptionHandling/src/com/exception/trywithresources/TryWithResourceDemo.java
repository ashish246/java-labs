package com.exception.trywithresources;

import java.io.IOException;
import java.io.InputStream;

public class TryWithResourceDemo {

	public static void main(String[] args) throws IOException {

		// printFile();

		printFileJava7();
	}

	/**
	 * Old Style using try-catch-finally
	 * 
	 * @throws IOException
	 */
	private static void printFile() throws IOException {
		InputStream input = null;

		try {

			input = ClassLoader.getSystemResourceAsStream("File.txt");

			int data = input.read();
			while (data != -1) {
				System.out.print((char) data);
				data = input.read();
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	/**
	 * Using try-with-resources.
	 * 
	 * When the try block finishes the FileInputStream will be closed
	 * automatically. This is possible because InputStream implements the Java
	 * interface java.lang.AutoCloseable. All classes implementing this
	 * interface can be used inside the try-with-resources construct.
	 * 
	 * @throws IOException
	 */
	private static void printFileJava7() throws IOException {

		try (InputStream input = ClassLoader
				.getSystemResourceAsStream("File.txt");) {

			int data = input.read();
			while (data != -1) {
				System.out.print((char) data);
				data = input.read();
			}
		}
	}

}
