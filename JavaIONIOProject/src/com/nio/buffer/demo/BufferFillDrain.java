package com.nio.buffer.demo;

import java.nio.CharBuffer;

/**
 * @author Administrator
 *
 */
public class BufferFillDrain
{
	public static void main(String[] argv) throws Exception
	{
		/*char [] myArray = new char [100];
		CharBuffer charbuffer = CharBuffer.wrap (myArray , 12, 42);*/
		
		CharBuffer buffer = CharBuffer.allocate(100);

		while (fillBuffer(buffer))
		{
			//buffer.limit( buffer.position() ).position(0);
			buffer.flip();
			drainBuffer(buffer);
			buffer.clear();
		}
	}

	private static void drainBuffer(CharBuffer buffer)
	{
		while (buffer.hasRemaining())
		{
			System.out.print(buffer.get());
		}

		System.out.println("");
	}

	private static boolean fillBuffer(CharBuffer buffer)
	{
		if (index >= strings.length)
		{
			return (false);
		}

		String string = strings[index++];

		for (int i = 0; i < string.length(); i++)
		{
			buffer.put(string.charAt(i));
		}

		return (true);
	}

	private static int index = 0;

	private static String[] strings = { "Some random string content 1",
			"Some random string content 2", "Some random string content 3",
			"Some random string content 4", "Some random string content 5",
			"Some random string content 6", };
}
