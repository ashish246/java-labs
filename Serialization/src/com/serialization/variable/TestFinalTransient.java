package com.serialization.variable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * http://blog.clempinch.com/transient-and-final-instance-variables-in-java/
 * 
 * During deserialization, the constructor of the object is not called. This is
 * a special object instantiation process handled by the JVM. For regular
 * non-final transient instance variable, they are assigned with their default
 * values.
 * 
 * If the final variable is blank, meaning that it is first initialized in the
 * object constructor(s) (like on line 63), the deserialization will put the
 * default value. That is why s2 = null.
 * 
 * On the contrary, for a final variable explicitly initialized, the value is
 * reaffected, but only if this initializations is done in a certain manner, in
 * fact, only if the initialization is done with what the Java specification
 * calls a compile-time constant expression (§15.28).
 * 
 * Constant expressions are the ones you are allowed to use in the case labels
 * of a switch statement. A constant expression is an expression, which can be
 * resolved at compile-time with no ambiguity: somehow, it gives the guarantee
 * that the resulting value will never change during the execution.
 * 
 * In our case, these are considered constant expressions and that is why the
 * values are reaffected to the final variables (even if they are transient).
 * 
 * literals (like a String on line 53, or an integer on line 57) + operation
 * between literals and reference to a constant final variables (like on line
 * 56) operation between integers (like on line 58) reference to constants (like
 * on line 59) There are other types of constant expressions (e.g. cast
 * operation to a primitive or a String). I suggest you to have a look at the
 * specifications for more details1.
 * 
 * However, object instantiations are not considered to be constant expressions
 * and that is why these are assigned to the default values (like on lines 55
 * and 60).
 *  
 * @author Administrator
 * 
 */
public class TestFinalTransient implements Serializable {

	private static final long serialVersionUID = 1L;
	private final transient String s1 = "test";
	private final transient String s2;
	private final transient String s3 = new String("hello");
	private final transient String s4 = s1 + s1 + 1;
	private final transient int i1 = 7;
	private final transient int i2 = 7 * 3;
	private final transient int i3 = Integer.MIN_VALUE;
	private final transient int[] a1 = { 1, 2, 3 };
	private final String s5 = "finalTest";
	private final String s6;// = "finalTest";
	private final static String s7 = "StaticS7";
	private static String s8 = new String("vjroif");;

	public TestFinalTransient() {
		s2 = "s2";
		s6 = "finalS6";
		//s8 = new String("sndsn");;
	}

	public static void main(String[] args) {

		File f = new File("diplo");
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new TestFinalTransient());
			oos.flush();
			oos.close();

			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			TestFinalTransient diplo = (TestFinalTransient) ois.readObject();
			ois.close();
			System.out.println("s1 = " + diplo.s1);
			System.out.println("s2 = " + diplo.s2);
			System.out.println("s3 = " + diplo.s3);
			System.out.println("s4 = " + diplo.s4);
			System.out.println("i1 = " + diplo.i1);
			System.out.println("i2 = " + diplo.i2);
			System.out.println("i3 = " + diplo.i3);
			System.out.println("a1 = " + diplo.a1);
			System.out.println("s5 = " + diplo.s5);
			System.out.println("s6 = " + diplo.s6);
			System.out.println("s7 = " + diplo.s7);
			System.out.println("s8 = " + diplo.s8);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
