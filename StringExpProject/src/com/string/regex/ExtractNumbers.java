package com.string.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractNumbers
{

	public static void main(String[] args)
	{

		String str = "AP0000000095";
		System.out.println("Order ID -> " + str.replaceAll("\\D+", ""));

		System.out.println("Order ID -> " + str.replaceAll("[^0-9]", ""));

		System.out.println("order id -> " + getSequenceNumberFromDocumentNo("AP0000000096"));

		usingPattern("AP0000000097");

		// Using Scanner
		Scanner scanner = new Scanner("AP0000000098").useDelimiter("[^0-9]+");

		while (scanner.hasNext())
		{
			if (scanner.hasNextInt())
			{
				System.out.println("scanned int id -> " + scanner.nextInt());
			}
			else{
				System.out.println("scanned string id -> " + scanner.next());
			}
		}
		scanner.close();
	}

	private static void usingPattern(String input)
	{
		// Pattern pattern = Pattern.compile("\\w+([0-9]+)\\w+([0-9]+)");
		Pattern pattern = Pattern.compile("(\\d+)");
		Matcher matcher = pattern.matcher(input);
		for (int i = 0; i < matcher.groupCount(); i++)
		{
			matcher.find();
			System.out.println(matcher.group());
		}
	}

	private static String getSequenceNumberFromDocumentNo(String pDocumentNo)
	{
		StringBuffer strBuff = new StringBuffer();
		char c;

		for (int i = 0; i < pDocumentNo.length(); i++)
		{
			c = pDocumentNo.charAt(i);

			if (Character.isDigit(c))
			{
				strBuff.append(c);
			}
		}

		return strBuff.toString();
	}
}
