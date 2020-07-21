package com.net.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * As you can see, I’ve put a 15 second delay in SimpleClient for the client to
 * wait before attempting to its message. It is important to notice that by the
 * time the client calls sleep(), he has already created the connection to the
 * server. What I’m going to do is start both threads and after the client makes
 * the connection I will suddenly stop the client application.
 * 
 * @author Administrator
 *
 */
public class SimpleClientApp
{

	public static void main(String[] args)
	{

		new Thread(new SimpleClient()).start();

	}

	static class SimpleClient implements Runnable
	{

		@Override
		public void run()
		{

			Socket socket = null;
			try
			{

				socket = new Socket("localhost", 3333);

				PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);

				System.out.println("Wait");

				Thread.sleep(1000);

				outWriter.println("Hello Mr. Server!");

			} catch (SocketException e)
			{
				e.printStackTrace();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			} catch (UnknownHostException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			} finally
			{
				try
				{
					if (socket != null)
						socket.close();
				} catch (IOException e)
				{

					e.printStackTrace();
				}
			}
		}
	}
}