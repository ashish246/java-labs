package com.nio.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteArrayDemo
{
	public static void main(String[] args) throws IOException
	{
		byteArrayToStream();

		fileToByte();

		readContentIntoByteArray();
	}

	/**
	 * Using IO
	 * 
	 * @return
	 */
	private static byte[] readContentIntoByteArray()
	{
		File file = new File("test.txt");

		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try
		{
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			for (int i = 0; i < bFile.length; i++)
			{
				System.out.print((char) bFile[i]);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return bFile;
	}

	/**
	 * Using NIO
	 * 
	 * @throws IOException
	 */
	public static void fileToByte() throws IOException
	{
		Path path = Paths.get("test.txt");
		byte[] data = Files.readAllBytes(path);

		for (int i = 0; i < data.length; i++)
		{
			System.out.print((char) data[i]);
		}
	}

	public static void byteArrayToStream()
	{
		String sampleString = "howtodoinjava.com";

		// Here converting string to input stream
		InputStream stream = new ByteArrayInputStream(sampleString.getBytes());
	}

}
