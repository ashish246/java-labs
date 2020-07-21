package com.io.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * RULE I: FileInputStream reads from the root or current working directory.
 * 
 * RULE II: GetResources.class.getResourceAsStream(): It will read the file from
 * the package of the class GetResources if path doesnt starts with "/"
 * 
 * RULE III: GetResources.class.getResourceAsStream(): It will read the file
 * from the package "./" in your bin or jar if path starts with "/"
 * 
 * RULE IV: GetResources.class.getClassLoader().getResourceAsStream(): It will
 * read file/path from the package "./" in your bin or jar
 * 
 * RULE V: File reads from the root or current working directory.
 * 
 * @author Administrator
 *
 */
public class GetResources
{

	public static void main(String[] args) throws IOException
	{

		Properties tProps = new Properties();

		// APPROACH - I
		FileInputStream fis = new FileInputStream("root.properties");
		tProps.load(fis);

		System.out.println("FolderName 1 -> " + tProps.getProperty("folderName"));

		// APPROACH - II
		Properties tProps1 = new Properties();
		InputStream is1 = GetResources.class.getResourceAsStream("member.properties");
		tProps1.load(is1);
		System.out.println("FolderName 2 -> " + tProps1.getProperty("folderName"));

		// APPROACH - III
		Properties tProps2 = new Properties();
		InputStream is2 = GetResources.class.getResourceAsStream("/src.properties");
		tProps2.load(is2);
		System.out.println("FolderName 3 -> " + tProps2.getProperty("folderName"));

		// APPROACH - IV
		Properties tProps3 = new Properties();
		/*
		 * InputStream is3 = GetResources.class.getClassLoader()
		 * .getResourceAsStream("src.properties");
		 */
		InputStream is3 = GetResources.class.getClassLoader().getResourceAsStream(
				"com/io/demo/member.properties");
		tProps3.load(is3);
		System.out.println("FolderName 4 -> " + tProps3.getProperty("folderName"));

		// APPROACH - V
		Properties tProps4 = new Properties();
		File file = new File("root.properties");

		tProps4.load(new FileInputStream(file));
		System.out.println("FolderName 5 -> " + tProps4.getProperty("folderName"));
		System.out.println("Absolute Path -> " + file.getAbsolutePath());
		System.out.println("Absolute Path -> " + file.getCanonicalPath());

	}
}
