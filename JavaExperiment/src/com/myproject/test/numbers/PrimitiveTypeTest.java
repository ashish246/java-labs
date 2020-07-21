package com.myproject.test.numbers;

/**
 * @author Administrator
 * 
 */
/**
 * There are some rules that are followed when selecting which overloaded method
 * to select, when Boxing, Widening, and Var-args are combined: -
 * 
 * Primitive widening uses the smallest method argument possible
 * 
 * 
 * Wrapper type cannot be widened to another Wrapper type
 * 
 * 
 * You can Box from int to Integer and widen to Object but no to Long
 * 
 * 
 * Widening beats Boxing, Boxing beats Var-args.
 * 
 * 
 * You can Box and then Widen (An int can become Object via Integer)
 * 
 * 
 * You cannot Widen and then Box (An int cannot become Long)
 * 
 * 
 * You cannot combine var-args, with either widening or boxing
 * 
 * @author Administrator
 * 
 */
public class PrimitiveTypeTest {

	public static void test(int... i) {
		for (int t = 0; t < i.length; t++) {
			System.out.println(i[t]);
		}
		System.out.println("int");
	}

	public static void test(float... f) {
		for (int t = 0; t < f.length; t++) {
			System.out.println(f[t]);
		}

		System.out.println("float");
	}

	public static void load(int i) {
		System.out.println("int");
	}

	public static void load(Short i) {
		System.out.println("float");
	}
	
	public static void load(String i) {
		System.out.println("String");
	}
	
	public static void load(String... i) {
		System.out.println("String...");
	}

	public static void main(String[] args) {
		short x = 1, y = 3;
		// test(x, y); // Compilation error here quoted as follows.
		//load(x);
		load("jcnkdjc");
	}
}