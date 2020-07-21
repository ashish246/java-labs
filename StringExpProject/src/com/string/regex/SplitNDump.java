package com.string.regex;

public class SplitNDump {
	static void dump(String[] arr) {
		for (String s : arr) {
			System.out.format("[%s]", s);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		dump("1,234,567,890".split(","));
		// "[1][234][567][890]"
		dump("1,234,567,890".split("(?=,)"));
		// "[1][,234][,567][,890]"
		dump("1,234,567,890".split("(?<=,)"));
		// "[1,][234,][567,][890]"
		dump("1,234,567,890".split("(?<=,)|(?=,)"));
		// "[1][,][234][,][567][,][890]"

		dump(":a:bb::c:".split("(?=:)|(?<=:)"));
		// "[][:][a][:][bb][:][:][c][:]"
		dump(":a:bb::c:".split("(?=(?!^):)|(?<=:)"));
		// "[:][a][:][bb][:][:][c][:]"
		dump(":::a::::b  b::c:".split("(?=(?!^):)(?<!:)|(?!:)(?<=:)"));
		// "[:::][a][::::][b  b][::][c][:]"
		dump("a,bb:::c  d..e".split("(?!^)\\b"));
		// "[a][,][bb][:::][c][  ][d][..][e]"

		dump("ArrayIndexOutOfBoundsException".split("(?<=[a-z])(?=[A-Z])"));
		// "[Array][Index][Out][Of][Bounds][Exception]"
		dump("1234567890".split("(?<=\\G.{4})"));
		// "[1234][5678][90]"

		// Split at the end of each run of letter
		dump("Boooyaaaah! Yippieeee!!".split("(?<=(?=(.)\\1(?!\\1))..)"));
		// "[Booo][yaaaa][h! Yipp][ieeee][!!]"
	}
}