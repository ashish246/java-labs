package com.io.demo;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleInputExample
{
	public static void main(String[] args)
	{
		usingConsoleReader();
		usingBufferedReader();
		usingScanner();
	}

	/**
	 * The evaluation of System.console() depends entirely on the environment
	 * you run application in it. As per my knowledge, both Eclipse and
	 * Netbeans will cause System.console() to return null whereas running your
	 * application on the same machine’s command line shall return a valid
	 * Console object.
	 */
	private static void usingConsoleReader()
	{
		Console console = null;
		String inputString = null;
		try
		{
			// creates a console object
			console = System.console();
			// if console is not null
			if (console != null)
			{
				// read line from the user input
				inputString = console.readLine("Name in console: ");
				// prints
				System.out.println("Name entered console: " + inputString);
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private static void usingBufferedReader()
	{
		System.out.println("Name in reader: ");
		try
		{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String inputString = bufferRead.readLine();

			System.out.println("Name entered reader: " + inputString);
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private static void usingScanner()
	{
		System.out.println("Name in scanner: ");

		Scanner scanIn = new Scanner(System.in);
		String inputString = scanIn.nextLine();

		scanIn.close();
		System.out.println("Name entered scanner: " + inputString);
	}

}
