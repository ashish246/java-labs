package com.exception.suppressed;

/**
 * We are able to see both exceptions when using try-with-resource in member
 * function.
 * 
 * @author Administrator
 * 
 */
public class SuppressedExceptionDemoWithTryWithResource {
	/**
	 * Demonstrating suppressed exceptions using try-with-resources
	 */
	public static void main(String[] arguments) throws Exception {
		try (DirtyResource resource = new DirtyResource()) {
			resource.accessResource();
		}
	}
}