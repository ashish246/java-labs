package com.concurrency.immutable;

/**
 * http://stackoverflow.com/questions/8445966/this-reference-escaping-during-
 * construction
 * http://stackoverflow.com/questions/20474521/allowing-the-this-reference
 * -to-escape
 * 
 * @author Administrator
 * 
 */

public class EscapeObjectReference {
	public EscapeObjectReference() {
		doSomething();
	}

	public void doSomething() {
		System.out.println("do something acceptable");
	}
}

/**
 * The problem is that the new Bar object is being registered with otherObject
 * before its construction is completed. Now if otherObject starts calling
 * methods on barObject, fields might not have been initialized, or barObject
 * might otherwise be in an inconsistent state. A reference to the barObject
 * (this to itself) has "escaped" into the rest of the system before it's ready.
 * 
 * Instead, if the setup() method is final on Foo, the Bar class can't put code
 * in there that will make the object visible before the Foo constructor
 * finishes.
 * 
 * @author Administrator
 * 
 */
class Bar extends EscapeObjectReference {
	public void doSomething() {
		System.out.println("yolo");
		Zoom zoom = new Zoom(this); // at this point 'this' might not be fully
									// initialized
	}
}

class Zoom {
	Zoom(Bar tBar) {

	}
}

/*
 * <p>Another example of "object reference escaping" </p> <p> final class
 * FooButton extends JButton { Foo() { super("Foo"); addActionListener(new
 * ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent e) { FooButton button =
 * FooButton.this; // (this kind of instantiation called Qualified This referred
 * in
 * http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.8.4)...
 * do something with the button } }); } }</p>
 */
