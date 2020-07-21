package com.string.regex;

/**
 * It uses zero-length matching regex with lookbehind and lookforward to find
 * where to insert spaces. Basically there are 3 patterns, and I use
 * String.format to put them together to make it more readable.
 * 
 * The three patterns are:
 * 
 * 
 * UC behind me, UC followed by LC in front of me
 * 
 * 
 * non-UC behind me, UC in front of me *
 * 
 * 
 * Letter behind me, non-letter in front of me *
 * 
 * 
 * @author Administrator
 *
 */
public class RegexDemoForName
{

	public static void main(String[] args)
	{

		String[] tests = { "lowercase", // [lowercase]
				"Class", // [Class]
				"MyClass", // [My Class]
				"HTML", // [HTML]
				"PDFLoader", // [PDF Loader]
				"AString", // [A String]
				"SimpleXMLParser", // [Simple XML Parser]
				"GL11Version", // [GL 11 Version]
				"99Bottles", // [99 Bottles]
				"May5", // [May 5]
				"BFG9000", // [BFG 9000]
		};
		for (String test : tests)
		{
			System.out.println("[" + splitCamelCase(test) + "]");
		}

		// System.out.println(splitCamelCase(""));

		testPasswordRegex();
	}

	static String splitCamelCase(String s)
	{
		return s.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
				"(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

	static void testPasswordRegex()
	{
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
		String passwd1 = "asdfgh1A";
		String passwd2 = "qwerty1A";
		String passwd3 = "qty1A";
		System.out.println(passwd1.matches(pattern));
		System.out.println(passwd2.matches(pattern));
		System.out.println(passwd3.matches(pattern));
	}

}
