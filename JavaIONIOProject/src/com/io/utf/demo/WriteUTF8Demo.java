package com.io.utf.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class WriteUTF8Demo
{

	public static void main(String[] args)
	{
		try
		{
			File fileDir = new File("test_utf.txt");
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileDir), "UTF8"));
			out.append("Howtodoinjava.com").append("\r\n");
			out.append("UTF-8 Demo").append("\r\n");
			out.append("क्षेत्रफल = लंबाई * चौड़ाई").append("\r\n");
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e)
		{
			System.out.println(e.getMessage());
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
