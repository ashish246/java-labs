package com.nio.demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileReader
{
	public static void main(String[] args) throws IOException
	{
		readFile1();
		
		readFile2();
		
		readFile3();
	}

	/**
	 * Read a small file in buffer of file size
	 */
	private static void readFile1()
	{
		try
		{
			RandomAccessFile aFile = new RandomAccessFile("test.txt", "r");
			FileChannel inChannel = aFile.getChannel();
			long fileSize = inChannel.size();
			ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
			inChannel.read(buffer);
			// buffer.rewind();
			buffer.flip();
			for (int i = 0; i < fileSize; i++)
			{
				System.out.print((char) buffer.get());
			}
			inChannel.close();
			aFile.close();
		} catch (IOException exc)
		{
			System.out.println(exc);
			System.exit(1);
		}
	}

	/**
	 * Read a large file in chunks with fixed size buffer
	 * 
	 * @throws IOException
	 */
	private static void readFile2() throws IOException
	{
		RandomAccessFile aFile = new RandomAccessFile("test.txt", "r");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (inChannel.read(buffer) > 0)
		{
			buffer.flip();
			for (int i = 0; i < buffer.limit(); i++)
			{
				System.out.print((char) buffer.get());
			}
			buffer.clear(); // do something with the data and clear/compact
							// it.
		}
		inChannel.close();
		aFile.close();
	}

	/**
	 * Faster file copy with mapped byte buffer
	 * 
	 * @throws IOException
	 */
	private static void readFile3() throws IOException
	{
		RandomAccessFile aFile = new RandomAccessFile("test.txt", "r");
		FileChannel inChannel = aFile.getChannel();
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0,
				inChannel.size());
		buffer.load();
		for (int i = 0; i < buffer.limit(); i++)
		{
			System.out.print((char) buffer.get());
		}
		buffer.clear(); // do something with the data and clear/compact it.
		inChannel.close();
		aFile.close();
	}
}
