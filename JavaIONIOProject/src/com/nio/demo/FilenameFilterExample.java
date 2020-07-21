package com.nio.demo;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterExample
{
	public static void main(String[] args)
	{
		// Delete all files from this directory
		String targetDirectory = ".";
		File dir = new File(targetDirectory);

		// Filter out all log files
		String[] logFiles = dir.list(new LogFilterFilter());

		// If no log file found; no need to go further
		if (logFiles.length == 0)
			return;

		// This code will delete all log files one by one
		for (String log : logFiles)
		{
			String tempLogFile = new StringBuffer(targetDirectory).append(File.separator)
					.append(log).toString();

			File fileDelete = new File(tempLogFile);

			boolean isdeleted = fileDelete.delete();

			System.out.println("file : " + tempLogFile + " is deleted : " + isdeleted);
		}
	}
}

// This filter will be used to check for all files if a file is log file
class LogFilterFilter implements FilenameFilter
{
	@Override
	public boolean accept(File dir, String fileName)
	{
		return (fileName.endsWith(".log"));
	}
}
