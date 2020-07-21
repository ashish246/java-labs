package com.nio.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TempFileDemo
{
	public static void main(String[] args)
	{
		tempUsingIO();

		tempUsingNIO();
	}

	public static void tempUsingIO()
	{
		File temp;
		try
		{
			temp = File.createTempFile("myTempFile", ".txt");

			System.out.println("Temp file created : " + temp.getAbsolutePath());

			// temp.delete(); //For deleting immediately

			temp.deleteOnExit(); // Delete on runtime exit

			System.out.println("Temp file exists : " + temp.exists());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void tempUsingNIO()
	{
		try
		{
			final Path path = Files.createTempFile("myTempFile", ".txt");
			System.out.println("Temp file : " + path);

			// Delete file on exit
			Files.deleteIfExists(path);

			// Delete file immediately
			Files.delete(path);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void writeInTemp()
	{
		try
		{
			final Path path = Files.createTempFile("myTempFile", ".txt");
			System.out.println("Temp file : " + path);

			// Writing data here
			byte[] buf = "some data".getBytes();
			Files.write(path, buf);

			// Delete file on exit
			path.toFile().deleteOnExit();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
