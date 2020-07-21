package com.exception.suppressed;

/**
 * in catch block we are able to access both exception. One as primary exception
 * and second as suppressed exception.
 * 
 * @author Administrator
 * 
 */
public class SuppressedExceptionDemoWithTryCatch {
	public static void memberFunction() throws Exception {
		try (DirtyResource resource = new DirtyResource()) {
			resource.accessResource();
		}
	}

	/**
	 * Executable member function demonstrating suppressed exceptions using
	 * try-with-resources
	 */
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
