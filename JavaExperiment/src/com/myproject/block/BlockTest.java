package com.myproject.block;

/**
 * You're creating a subclass of Temp. For each class, any instance initializers
 * are executed before the constructor body - but the superclass goes through
 * initialization before the subclass initialization. So the execution flow is:
 * 
 * Initializers in Object
 * 
 * Constructor body in Object
 * 
 * Initializers in Temp
 * 
 * Constructor body in Temp
 * 
 * Initializers in anonymous class
 * 
 * Constructor body in anonymous class (none)
 * 
 * I would strongly advise you to refactor any code which looked like this
 * anyway - aim for clarity rather than cleverness.
 * 
 * @author Administrator
 * 
 */
public class BlockTest {
	public static void main(String... arg) {
		Temp t = new Temp() {
			{
				{
					System.out.println("Static child");
				}
				System.out.println(" instance initialize");
			}
		};

	}
}

class Temp {
	int i;

	{
		i = 9;
		System.out.println("Static" + i);
	}

	Temp() {
		System.out.println("Temp const " + i);
	}
}
