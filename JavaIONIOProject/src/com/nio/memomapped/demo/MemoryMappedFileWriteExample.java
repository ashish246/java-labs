package com.nio.memomapped.demo;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedFileWriteExample
{
	private static String bigExcelFile = "test_out.txt";

	public static void main(String[] args) throws Exception
	{
		// Create file object
		File file = new File(bigExcelFile);

		// Delete the file; we will create a new file
		file.delete();

		// Get file channel in readonly mode
		FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();

		// Get direct byte buffer access using channel.map() operation
		MappedByteBuffer buffer = fileChannel
				.map(FileChannel.MapMode.READ_WRITE, 0, 4096 * 8 * 8);

		// Write the content using put methods
		buffer.put("howtodoinjava.com".getBytes());
	}
}