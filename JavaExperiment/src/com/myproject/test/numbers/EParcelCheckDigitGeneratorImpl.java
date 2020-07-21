package com.myproject.test.numbers;

/**
 * @author albertp2
 *         <p>
 *         This is the eParcel implementation of the Check Digit Generator.
 *         </p>
 */
public class EParcelCheckDigitGeneratorImpl
{

	private static final int MULTIPLICATION_FACTOR = 3;

	private static final int BASE_FACTOR = 10;

	/**
	 * <li>Step 1: replace all alpha characters with the last digit of the ASCI
	 * representation.</li> <li>Step 2: Starting with the first digit on the
	 * right, add all alternate numbers.</li> <li>Step 3: Multiply the result
	 * by 3.</li> <li>Step 4: Starting with the second number on the right, add
	 * all the alternate numbers.</li> <li>Step 5: Add the results of Step 2
	 * and Step 3.</li> <li>Step 6: Add the number needed to bring the total to
	 * the next multiple of ten. If the result is divisible by 10 then the
	 * check digit is 0.</li>
	 * <p>
	 * E.g. for this input string: 7MX0012345670100060090, the check digit
	 * would be 4.
	 * </p>
	 */
	public int generateCheckDigit(final String text)
	{

		String asciiStr = replaceAlphaCharsWithLastDigitASCII(text);
		int total1 = getTotalOfAlternateNumbersInString(asciiStr,
				StringUtils.length(asciiStr) - 1);
		total1 = total1 * MULTIPLICATION_FACTOR;
		int total2 = getTotalOfAlternateNumbersInString(asciiStr,
				StringUtils.length(asciiStr) - 2);
		int total = total1 + total2;

		int checkDigit = 0;
		if (total % BASE_FACTOR != 0)
		{
			checkDigit = BASE_FACTOR - (total % BASE_FACTOR);
		}

		return checkDigit;
	}

	/**
	 * Concatenates the last digit of the ASCII representation (or numeric if
	 * the character is a digit) of each character in a string.
	 * 
	 * @param str
	 * @return
	 */
	private String replaceAlphaCharsWithLastDigitASCII(final String str)
	{
		StringBuilder asciiStr = new StringBuilder("");

		if (StringUtils.isNotBlank(str))
		{
			for (int i = 0; i < str.length(); i++)
			{
				char j = str.charAt(i);
				if (Character.isDigit(j))
				{
					asciiStr.append(j);
				} else
				{
					asciiStr.append(StringUtils.substring(Integer.toString(j), 1));
				}
			}
		}

		return asciiStr.toString();
	}

	/**
	 * Adds each alternate numeric character in a string (from the right,
	 * starting from a given index) and returns the total.
	 * <p>
	 * E.g. For this string: 7780012345670100060090, if the starting index is
	 * last character in the string, then the Result would be:
	 * 0+0+6+0+1+7+5+3+1+0+7 = 30
	 * </p>
	 * 
	 * @param str
	 *             the string containing the numeric characters
	 * @param startingIndex
	 *             the index to start from (will start from the right of the
	 *             string)
	 * @return
	 */
	private int getTotalOfAlternateNumbersInString(final String str, final int startingIndex)
	{

		int total = 0;

		for (int i = startingIndex; i >= 0; i -= 2)
		{
			char j = str.charAt(i);
			total += Integer.valueOf(Character.toString(j));
		}

		return total;
	}

}
