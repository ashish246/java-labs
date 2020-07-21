package com.myproject.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Strange for two reasons. First, it should have flagged the validation error,
 * and second the date object obtained is completely useless. So, what went
 * wrong here.
 * 
 * Well, error is in parsing logic. parse() method uses positions of pattern
 * keywords in DATE_PATTERN and uses it to parse input string. It is not
 * intelligent by default to use the right characters for parsing and it uses
 * what comes on its way (even slashes).
 * 
 * @author Administrator
 * 
 */
public class TestSetLenient {
	private static final String DATE_PATTERN = "MM/dd/yyyy";

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(DATE_PATTERN);
		try {
			// Lenient conversion result in unexpected output
			Date d = sdf.parse("2012/12/17");
			System.out.println(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			/**
			 * Make is strict validation [SHOULD THROW ERROR]
			 * */
			sdf.setLenient(false);

			// Strict conversion validates the date correctly
			Date d1 = sdf.parse("2012/12/17");
			System.out.println(d1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			/**
			 * Make is strict validation [SHOULD PASS]
			 * */
			sdf.setLenient(false);

			// Strict conversion validates the date correctly
			Date d2 = sdf.parse("12/17/2012");
			System.out.println("d2->"+d2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
