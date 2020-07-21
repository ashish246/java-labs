package com.io.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOFileReader
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = null;
		String sCurrentLine = null;
		try
		{
			br = new BufferedReader(new FileReader("test.txt"));
			while ((sCurrentLine = br.readLine()) != null)
			{
				System.out.println(sCurrentLine);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (br != null)
					br.close();
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
