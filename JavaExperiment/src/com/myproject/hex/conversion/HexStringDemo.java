package com.myproject.hex.conversion;

/**
 * The conversion process is depend on this formula “Hex<==>Decimal<==>ASCII“.
 * 
 * ASCII to Hex –
 * 
 * 
 * • Convert String to char array • Cast it to Integer • Use
 * Integer.toHexString() to convert it to Hex
 * 
 * 
 * • Hex to ASCII –
 * 
 * 
 * Cut the Hex value in pairs format, convert it to radix 16 interger(decimal)
 * Integer.parseInt(hex, 16), and cast it back to char. • Cut the Hex value in 2
 * chars groups • Convert it to base 16 Integer using Integer.parseInt(hex, 16)
 * and cast to char • Append all chars in StringBuilder
 * 
 * 
 * @author Administrator
 *
 */
public class HexStringDemo {

	public static void main(String[] args) {

		HexStringDemo strToHex = new HexStringDemo();
		System.out.println("\n***** Convert ASCII to Hex *****");
		String str = "I Love Java!";
		System.out.println("Original input : " + str);

		String hex = strToHex.convertStringToHex(str);

		System.out.println("Hex : " + hex);

		System.out.println("\n***** Convert Hex to ASCII *****");
		System.out.println("Hex : " + hex);
		System.out.println("ASCII : " + strToHex.convertHexToString(hex));
	}

	public String convertStringToHex(String str) {

		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}

		return hex.toString();
	}

	public String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}

		System.out.println("Decimal : " + temp.toString());

		return sb.toString();
	}

}