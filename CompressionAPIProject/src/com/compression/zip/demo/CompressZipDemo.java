package com.compression.zip.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressZipDemo
{
	public static void main(String[] args)
	{
		byte[] buffer = new byte[1024];

		try
		{
			FileOutputStream fos = new FileOutputStream("testZip.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry("testZip.txt");
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream("testZip.txt");

			int len;
			while ((len = in.read(buffer)) > 0)
			{
				zos.write(buffer, 0, len);
			}

			in.close();
			zos.closeEntry();

			// remember close it
			zos.close();

			System.out.println("Done");

		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}