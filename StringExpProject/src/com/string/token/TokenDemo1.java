package com.string.token;

import java.util.Arrays;

public class TokenDemo1 {

	static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

	public static void main(String[] args) {

		System.out.println(Arrays.toString("a;b;c;d".split("(?<=;)")));
		System.out.println(Arrays.toString("a;b;c;d".split("(?=;)")));
		System.out.println(Arrays.toString("a;b;c;d".split("((?<=;)|(?=;))")));

		// Readability imporved
		final String[] aEach = "a;b;c;d".split(String.format(WITH_DELIMITER,
				";"));
		System.out.println(Arrays.toString(aEach));
	}
}
