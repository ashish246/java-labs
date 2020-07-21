package com.nio.memomapped.demo;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedFileReadExample
{
	private static String bigExcelFile = "fileHex.hex";

	public static void main(String[] args) throws Exception
	{
		// Create file object
		File file = new File(bigExcelFile);

		// Get file channel in readonly mode
		FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();

		// Get direct byte buffer access using channel.map() operation
		MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0,
				fileChannel.size());

		// the buffer now reads the file as if it were loaded in memory.
		System.out.println(buffer.isLoaded()); // prints false
		System.out.println(buffer.capacity()); // Get the size based on
										// content size of file

		// You can read the file from this buffer the way you like.
		for (int i = 0; i < buffer.limit(); i++)
		{
			System.out.print((char) buffer.get()); // Print the content of
											// file
		}
	}
}