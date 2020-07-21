package com.exception.test;

/**
 * If a catch block handles more than one exception type, then the catch
 * parameter is implicitly final. In this example, the catch parameter ex is
 * final and therefore you cannot assign any values to it within the catch
 * block.
 * 
 * If you compile below code with java 6 or earlier release, It will give
 * compilation error and will ask you do to declare throws clause.
 * 
 * Final Rethow : Allows you to catch an exception type and it's subtype and
 * rethrow it without having to add a throws clause to the method signature.
 * 
 * @author Administrator
 * 
 */
public class MultipleExceptionsInCatchBlock {

	public static void main(String[] args) {
		sampleMethod();
	}

	public static void sampleMethod()
	// throws Throwable //No need to do this
	{
		try {
			// Do some processing which throws NullPointerException; I am
			// sending directly
			throw new NullPointerException();
		}

		// You can catch multiple exception added after 'pipe' character
		catch (NullPointerException | IndexOutOfBoundsException ex) {
			throw ex;
		}

		// Now method sampleMethod() do not need to have 'throws' clause
		catch (Throwable ex) {
			throw ex;
		}
	}
}