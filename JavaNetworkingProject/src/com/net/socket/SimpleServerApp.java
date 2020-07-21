package com.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * It consists of two threads. The first one, SimpleServer, opens a socket on
 * the local machine on port 3333. Then it waits for a connection to come in.
 * When it finally receives a connection, it creates an input stream out of it,
 * and simply reads one line of text from the client that was connected. The
 * second thread, SimpleClient, attempts to connect to the server socket that
 * SimpleServer opened. When it does so, it sends a line of text and that’s it.
 * This time though the two threads will be in different classes launched by two
 * different main methods, so as to cause a SocketEception:
 * 
 * @author Administrator
 *
 */
public class SimpleServerApp
{

	public static void main(String[] args) throws InterruptedException
	{

		new Thread(new SimpleServer()).start();

	}

	static class SimpleServer implements Runnable
	{

		@Override
		public void run()
		{

			ServerSocket serverSocket = null;

			try
			{
				serverSocket = new ServerSocket(3333);
				serverSocket.setSoTimeout(0);

				while (true)
				{
					try
					{
						Socket clientSocket = serverSocket.accept();

						BufferedReader inputReader = new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()));

						System.out.println("Client said :" + inputReader.readLine());

					} catch (SocketTimeoutException e)
					{
						e.printStackTrace();
					}
				}

			} catch (IOException e1)
			{
				e1.printStackTrace();
			} finally
			{
				try
				{
					if (serverSocket != null)
					{
						serverSocket.close();
					}
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
