package com.io.demo;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Delete a directory with all files inside it
 * 
 * It works in two steps recursively:
 * 
 * It first delete all files in a directory; then It delete the directory itself
 * 
 * @author Administrator
 *
 */
public class DeleteFilesExample
{
	public static void main(String[] args)
	{
		Path dir = Paths.get("temp");
		try
		{
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
						throws IOException
				{
					System.out.println("Deleting file: " + file);
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc)
						throws IOException
				{
					System.out.println("Deleting dir: " + dir);
					if (exc == null)
					{
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					} else
					{
						throw exc;
					}
				}

			});
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}