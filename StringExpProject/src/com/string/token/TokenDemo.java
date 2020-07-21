package com.string.token;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Administrator
 *
 */
public class TokenDemo {

	public static void main(String[] args) {

		String s = ",abd,def,,ghi,";

		System.err.println("--- String.split Output ---");

		String[] tokens = s.split(",");
		System.err.println(String.format("%s -> %s", s, Arrays.asList(tokens)));

		for (int i = 0; i < tokens.length; ++i) {
			System.err.println(String.format("tokens[%d] = %s", i, tokens[i]));
		}

		// If [the limit] is non-positive then the pattern will be applied as
		// many times as possible and the array can have any length. If [the
		// limit] is zero then the pattern will be applied as many times as
		// possible, the array can have any length, and trailing empty strings
		// will be discarded.
		System.err
				.println("--- String.split with non-positive limit Output ---");
		String[] tokens1 = s.split(",", -1);
		System.err
				.println(String.format("%s -> %s", s, Arrays.asList(tokens1)));

		for (int i = 0; i < tokens1.length; ++i) {
			System.err.println(String.format("tokens[%d] = %s", i, tokens1[i]));
		}

		System.err.println("--- Scanner Output ---");

		Scanner sc = new Scanner(s);
		sc.useDelimiter(",");
		while (sc.hasNext()) {
			System.err.println(sc.next());
		}
		sc.close();

		System.err.println("--- StringTokenizer Output ---");

		StringTokenizer tok = new StringTokenizer(s, ",");
		while (tok.hasMoreTokens()) {
			System.err.println(tok.nextToken());
		}
	}
}
