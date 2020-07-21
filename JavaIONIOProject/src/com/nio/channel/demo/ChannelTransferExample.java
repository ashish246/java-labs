package com.nio.channel.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelTransferExample
{
	public static void main(String[] argv) throws Exception
	{
		// Input files
		String[] inputFiles = new String[] { "test.txt"};

		// Files contents will be written in these files
		String outputFile = "test_out.txt";

		// Get channel for output file
		FileOutputStream fos = new FileOutputStream(new File(outputFile));
		WritableByteChannel targetChannel = fos.getChannel();

		for (int i = 0; i < inputFiles.length; i++)
		{
			// Get channel for input files
			FileInputStream fis = new FileInputStream(inputFiles[i]);
			FileChannel inputChannel = fis.getChannel();

			// Transfer data from input channel to output channel
			inputChannel.transferTo(0, inputChannel.size(), targetChannel);

			// close the input channel
			inputChannel.close();
			fis.close();
		}

		// finally close the target channel
		targetChannel.close();
		fos.close();
	}
}
