package com.exception.suppressed;

/**
 * For example while writing to output stream, an exception can be thrown from
 * the try block, and up to two exceptions can be thrown from the
 * try-with-resources statement when it tries to close the stream. If an
 * exception is thrown from the try block and one or more exceptions are thrown
 * from the try-with-resources statement, then those exceptions thrown from the
 * try-with-resources statement are suppressed, and the exception thrown by the
 * block is the one that is thrown by the closeStream() method.
 * 
 * You can retrieve these suppressed exceptions by calling the
 * Throwable.getSuppressed method from the exception thrown by the try block
 * 
 * ***As it was prior to suppressed exceptions support*****
 * 
 * Suppressed exceptions, as name suggest, are exceptions thrown in the code but
 * were ignored somehow. If you remember try-catch-finally block execution
 * sequence and how they return any value or exceptions, you will recall that
 * exceptions thrown in finally block are suppressed is exception is thrown in
 * try block also. Before java 7, you was informed about these exceptions by
 * logging if implemented, but you didn’t have any control over these types of
 * exceptions once finally block is over.
 * 
 * @author Administrator
 * 
 */
public class SuppressedExceptionDemoWithTryFinallyPrevious {
	/**
	 * Executable member function demonstrating suppressed exceptions One
	 * exception is lost if not added in suppressed exceptions list
	 */
	public static void memberFunction() throws Exception {
		DirtyResource resource = new DirtyResource();
		try {
			resource.accessResource();
		} finally {
			resource.close();
		}
	}

	public static void main(String[] arguments) throws Exception {
		try {
			memberFunction();
		} catch (Exception ex) {
			System.err.println("Exception encountered: " + ex.toString());
			final Throwable[] suppressedExceptions = ex.getSuppressed();
			final int numSuppressed = suppressedExceptions.length;
			if (numSuppressed > 0) {
				System.err.println("tThere are " + numSuppressed
						+ " suppressed exceptions:");
				for (final Throwable exception : suppressedExceptions) {
					System.err.println("tt" + exception.toString());
				}
			}
		}
	}
}

class DirtyResource implements AutoCloseable {
	/**
	 * Need to call this method if you want to access this resource
	 * 
	 * @throws RuntimeException
	 *             no matter how you call this method
	 * */
	public void accessResource() {
		throw new RuntimeException(
				"I wanted to access this resource. Bad luck. Its dirty resource !!!");
	}

	/**
	 * The overridden closure method from AutoCloseable interface
	 * 
	 * @throws Exception
	 *             which is thrown during closure of this dirty resource
	 * */
	@Override
	public void close() throws Exception {
		throw new NullPointerException(
				"Remember me. I am your worst nightmare !! I am Null pointer exception !!");
	}
}