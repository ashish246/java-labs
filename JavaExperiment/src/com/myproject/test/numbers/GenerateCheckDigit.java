package com.myproject.test.numbers;

public class GenerateCheckDigit {

	public static void main(String[] args) {

		String pArticleID = "3MZ00000030100061080";

		for (int i = 0; i < pArticleID.length(); i++) {
			if (pArticleID.substring(i, i + 1).matches("[a-zA-Z]+")) {
				// get the ASCII of the ith Character if its an alphabet
				int ascii = pArticleID.charAt(i);

				Integer tSecondDigit = getNthDigit(ascii, 10, 1);

				String tNewChar = tSecondDigit.toString();
				pArticleID = pArticleID.replace(pArticleID.charAt(i),
						tNewChar.charAt(0));
			}
		}

		Integer totalSum1 = 0;
		for (int i = pArticleID.length(); i > 0; i--) {

			char tChar = pArticleID.charAt(i - 1);
			int tInt = Character.getNumericValue(tChar);
			totalSum1 = totalSum1 + tInt;
			i--;
		}
		totalSum1 = totalSum1 * 3;
		System.out.println("totalSum1: " + totalSum1);

		Integer totalSum2 = 0;
		for (int i = pArticleID.length(); i > 0; i--) {
			if (i > 1) {
				char tChar = pArticleID.charAt(i - 2);
				int tInt = Character.getNumericValue(tChar);
				totalSum2 = totalSum2 + tInt;
				i--;
			}
		}
		System.out.println("totalSum2: " + totalSum2);

		Integer totalSum3 = totalSum1 + totalSum2;

		Integer tCheckDigit = 0;
		if ((totalSum3 % 10) > 0) {
			tCheckDigit = (10 - (totalSum3 % 10));
		} else {
			tCheckDigit = 0;
		}

		System.out.println("tCheckDigit : " + tCheckDigit);

		pArticleID = pArticleID.concat(tCheckDigit.toString());

		System.out.println("Article ID: " + pArticleID);

	}

	public static int getNthDigit(int number, int base, int n) {
		return (int) ((number / Math.pow(base, n - 1)) % base);
	}

}
