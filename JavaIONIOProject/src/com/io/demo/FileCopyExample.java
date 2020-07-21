package com.io.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;

public class FileCopyExample
{
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException
	{
		fileCopyUsingApacheCommons();

		fileCopyUsingNIOFilesClass();
		
		fileCopyUsingFileStreams();
	}

	private static void fileCopyUsingFileStreams() throws IOException
	{
		File fileToCopy = new File("test.txt");
		FileInputStream input = new FileInputStream(fileToCopy);

		File newFile = new File("src/test.txt");
		FileOutputStream output = new FileOutputStream(newFile);

		byte[] buf = new byte[1024];
		int bytesRead;

		while ((bytesRead = input.read(buf)) > 0)
		{
			output.write(buf, 0, bytesRead);
		}

		input.close();
		output.close();
	}

	private static void fileCopyUsingNIOChannelClass() throws IOException
	{
		File fileToCopy = new File("test.txt");
		FileInputStream inputStream = new FileInputStream(fileToCopy);
		FileChannel inChannel = inputStream.getChannel();

		File newFile = new File("src/test.txt");
		FileOutputStream outputStream = new FileOutputStream(newFile);
		FileChannel outChannel = outputStream.getChannel();

		inChannel.transferTo(0, fileToCopy.length(), outChannel);

		inputStream.close();
		outputStream.close();
	}

	private static void fileCopyUsingNIOFilesClass() throws IOException
	{
		Path source = Paths.get("test.txt");
		Path destination = Paths.get("src/test.txt");

		Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
	}

	private static void fileCopyUsingApacheCommons() throws IOException
	{
		File fileToCopy = new File("test.txt");
		File newFile = new File("src/test.txt");

		// Apache commons jar
		// FileUtils.copyFile(fileToCopy, newFile);

		// OR

		// IOUtils.copy(new FileInputStream(fileToCopy), new
		// FileOutputStream(newFile));
	}
}
