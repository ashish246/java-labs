package com.nio.memomapped.demo;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * To do both writing and reading in memory mapped files, we start with a
 * RandomAccessFile, get a channel for that file. Memory mapped byte buffers are
 * created via the FileChannel.map() method. This class extends the ByteBuffer
 * class with operations that are specific to memory-mapped file regions. A
 * mapped byte buffer and the file mapping that it represents remain valid until
 * the buffer itself is garbage-collected. Note that you must specify the
 * starting point and the length of the region that you want to map in the file;
 * this means that you have the option to map smaller regions of a large file.
 * 
 * 
 * The file created with the above program is 128 MB long, which is probably
 * larger than the space your OS will allow. The file appears to be accessible
 * all at once because only portions of it are brought into memory, and other
 * parts are swapped out. This way a very large file (up to 2 GB) can easily be
 * modified.
 * 
 * @author Administrator
 *
 */
public class MemoryMappedFileExample
{
	static int length = 0x8FFFFFF; // 128 Mb

	public static void main(String[] args) throws Exception
	{
		MappedByteBuffer out = new RandomAccessFile("test.txt", "rw").getChannel().map(
				FileChannel.MapMode.READ_WRITE, 0, length);
		for (int i = 0; i < length; i++)
		{
			out.put((byte) 'x');
		}
		System.out.println("Finished writing");

	}
}