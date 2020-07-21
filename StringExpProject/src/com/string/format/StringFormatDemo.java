package com.string.format;

/**
 * %a Floating-point hexadecimal %A
 * 
 * %b Boolean %B
 * 
 * %c Character %C
 * 
 * %d Decimal integer
 * 
 * %h Hash code of the argument %H
 * 
 * %e Scientific notation %E
 * 
 * %f Decimal floating-point
 * 
 * %g Uses %e or %f, whichever is shorter %G
 * 
 * %o Octal integer
 * 
 * %n Inserts a newline character
 * 
 * %s String %S
 * 
 * %t Time and date %T
 * 
 * %x Integer hexadecimal %X
 * 
 * %% Inserts a % sign
 * 
 * 
 * for "%b" : If the argument arg is null, then the result is "false". If arg is
 * a boolean or Boolean, then the result is the string returned by
 * String.valueOf(). Otherwise, the result is "true".
 * 
 * The format specifier "%d" menas that an int will be treated as a 32-bit
 * signed decimal number. However, the format specifier "%hd" means that a short
 * will be converted instead.
 * 
 * width specifier: If the format specifier is "%6d", and the supplied int
 * argument is 52, then the output will be "    52" (four spaces on the left)
 * 
 * @author Administrator
 *
 */
public class StringFormatDemo {

	public static void main(String[] args) {

		double x = 123.456;
		char c = 65;
		int i = 65;

		System.out.printf("%s %n", x);
		System.out.printf("%s %s %n", x, "XXX");
		System.out.printf("%b %n", x);
		System.out.printf("%c %n", c);
		System.out.printf("%5.0f %n", x);
		System.out.printf("%10.5f %n", x);
		System.out.printf("%d %n", i);

		System.out.printf("My friend %s is %d years old.", new Object[] {
				"Ashish", new Integer(31) });
	}

}
