package com.myproject.cloning;

public class InMemoryTest {

	public static void main(String[] args) throws Exception {
		// Create instance of serializable object
		SerializableClass myClass = new SerializableClass("Lokesh", "Gupta");
		// Verify the content
		System.out.println(myClass);

		// Now create a deep copy of it
		SerializableClass deepCopiedInstance = myClass.deepCopy();
		// Again verify the content
		System.out.println(deepCopiedInstance);
	}
}