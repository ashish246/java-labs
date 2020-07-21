package com.keyword.strings;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ToStringExample {

	/**
	 * Build a Van object and display its textual representation.
	 * 
	 * Note that the Collection classes have their own implementation of
	 * <code>toString</code>, as exemplified here by the List field holding the
	 * Options.
	 */
	public static void main(String... arguments) {
		List<String> options = new ArrayList<>();
		options.add("Air Conditioning");
		options.add("Leather Interior");

		ToStringExample van = new ToStringExample("Dodge", 4, new Date(), "Blue", options);
		System.out.println(van);
	}

	public ToStringExample(String aName, int aNumDoors, Date aYearManufactured,
			String aColor, List<String> aOptions) {
		fName = aName;
		fNumDoors = aNumDoors;
		fYearManufactured = aYearManufactured;
		fColor = aColor;
		fOptions = aOptions;
	}

	// ..other methods elided

	/**
	 * Intended only for debugging.
	 * 
	 * <P>
	 * Here, a generic implementation uses reflection to print names and values
	 * of all fields <em>declared in this class</em>. Note that superclass
	 * fields are left out of this implementation.
	 * 
	 * <p>
	 * The format of the presentation could be standardized by using a
	 * MessageFormat object with a standard pattern.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		// determine fields declared in this class only (no fields of
		// superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				// requires access to private field:
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}

	// PRIVATE
	private String fName;
	private int fNumDoors;
	private Date fYearManufactured;
	private String fColor;
	private List<String> fOptions;
}
