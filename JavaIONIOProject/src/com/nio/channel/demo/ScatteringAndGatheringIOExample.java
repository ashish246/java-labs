package com.nio.channel.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author Administrator
 *
 */
public class ScatteringAndGatheringIOExample
{
	public static void main(String params[])
	{
		String data = "Scattering and Gathering example shown in howtodoinjava.com";

		gatherBytes(data);
		scatterBytes();
	}

	/*
	 * gatherBytes() reads bytes from different buffers and writes to file
	 * channel. Note that it uses a single write for both the buffers.
	 */
	public static void gatherBytes(String data)
	{
		// First Buffer holds a random number
		ByteBuffer bufferOne = ByteBuffer.allocate(4);

		// Second Buffer holds data we want to write
		ByteBuffer buffer2 = ByteBuffer.allocate(200);

		// Writing Data sets to Buffer
		bufferOne.asIntBuffer().put(13);
		buffer2.asCharBuffer().put(data);

		// Calls FileOutputStream(file).getChannel()
		GatheringByteChannel gatherer = createChannelInstance("test.txt", true);

		// Write data to file
		try
		{
			gatherer.write(new ByteBuffer[] { bufferOne, buffer2 });
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * scatterBytes() read bytes from a file channel into a set of buffers.
	 * Note that it uses a single read for both the buffers.
	 */
	public static void scatterBytes()
	{
		// First Buffer holds a random number
		ByteBuffer bufferOne = ByteBuffer.allocate(4);

		// Second Buffer holds data we want to write
		ByteBuffer bufferTwo = ByteBuffer.allocate(200);

		// Calls FileInputStream(file).getChannel()
		ScatteringByteChannel scatterer = createChannelInstance("test.txt", false);

		try
		{
			// Reading from the channel
			scatterer.read(new ByteBuffer[] { bufferOne, bufferTwo });
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// Read the buffers seperately
		bufferOne.rewind();
		bufferTwo.rewind();

		int bufferOneContent = bufferOne.asIntBuffer().get();
		String bufferTwoContent = bufferTwo.asCharBuffer().toString();

		// Verify the content
		System.out.println(bufferOneContent);
		System.out.println(bufferTwoContent);
	}

	public static FileChannel createChannelInstance(String file, boolean isOutput)
	{
		FileChannel fc = null;
		try
		{
			if (isOutput)
			{
				fc = new FileOutputStream(file).getChannel();
			} else
			{
				fc = new FileInputStream(file).getChannel();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fc;
	}
}