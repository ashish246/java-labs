package com.nio.path.demo;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;

/**
 * 
 * Define absolute path
 * 
 * Define path relative to file store root
 * 
 * Define path relative to current working directory
 * 
 * Define path from URI scheme
 * 
 * Define path using file system default
 * 
 * Using System.getProperty() to build path
 * 
 * @author Administrator
 *
 */
public class WorkingWithNIOPath
{
	public static void main(String[] args)
	{
		defineAbsolutePath();
		defineRelativePathToRoot();
		defineRelativePathToWorkingFolder();
		definePathFromURI();
		UsingFileSystemGetDefault();
		UsingSystemProperty();
	}

	// Starts with file store root or drive
	private static void defineAbsolutePath()
	{
		// Starts with file store root or drive
		Path absolutePath1 = Paths.get(
				"E:/Projects/My_Projects/Shared/TechStuff/AT/Java/JavaIONIOProject/src",
				"src.properties");
		Path absolutePath2 = Paths.get("E:/Projects/My_Projects/Shared/TechStuff/AT/Java",
				"JavaIONIOProject/src", "src.properties");
		Path absolutePath3 = Paths.get("E:/Projects/My_Projects", "Shared/TechStuff/AT/Java",
				"JavaIONIOProject/src", "src.properties");

		System.out.println(absolutePath1.toString());
		System.out.println(absolutePath2.toString());
		System.out.println(absolutePath3.toString());
	}

	// Starts with a "/"
	/**
	 * Path relative to file store root starts with a forward slash (“/”)
	 * character.
	 */
	private static void defineRelativePathToRoot()
	{
		// How to define path relative to file store root (in windows it is
		// c:/)
		Path relativePath1 = FileSystems.getDefault().getPath(
				"/Projects/My_Projects/Shared/TechStuff/AT/Java/JavaIONIOProject/src",
				"src.properties");
		Path relativePath2 = FileSystems.getDefault().getPath("/Projects",
				"My_Projects/Shared/TechStuff/AT/Java/JavaIONIOProject/src", "src.properties");

		System.out.println(relativePath1.toAbsolutePath().toString());
		System.out.println(relativePath2.toAbsolutePath().toString());
	}

	// Starts without a "/"
	/**
	 * To define NIO path relative to current working directory, do not use
	 * either file system root (c:/ in windows) or slash (“/”) also.
	 */
	private static void defineRelativePathToWorkingFolder()
	{
		// How to define path relative to current working directory
		Path relativePath1 = Paths.get("src", "src.properties");
		System.out.println(relativePath1.toAbsolutePath().toString());
	}

	private static void definePathFromURI()
	{
		// URI uri =
		// URI.create("file:///c:/Lokesh/Setup/workspace/NIOExamples/src/src.properties");
		// //OR
		URI uri = URI
				.create("file:///E:/Projects/My_Projects/Shared/TechStuff/AT/Java/JavaIONIOProject/src/src.properties");

		String scheme = uri.getScheme();
		if (scheme == null)
			throw new IllegalArgumentException("Missing scheme");

		// check for default provider to avoid loading of installed providers
		if (scheme.equalsIgnoreCase("file"))
			System.out.println(FileSystems.getDefault().provider().getPath(uri).toAbsolutePath()
					.toString());

		// try to find provider
		for (FileSystemProvider provider : FileSystemProvider.installedProviders())
		{
			if (provider.getScheme().equalsIgnoreCase(scheme))
			{
				System.out.println(provider.getPath(uri).toAbsolutePath().toString());
				break;
			}
		}
	}

	/**
	 * Define path using file system default
	 * 
	 */
	private static void UsingFileSystemGetDefault()
	{
		FileSystem fs = FileSystems.getDefault();
		Path path1 = fs.getPath("src/src.properties");
		Path path2 = fs.getPath(
				"E:/Projects/My_Projects/Shared/TechStuff/AT/Java/JavaIONIOProject/src",
				"src.properties");

		System.out.println(path1.toAbsolutePath().toString());
		System.out.println(path2.toAbsolutePath().toString());
	}

	private static void UsingSystemProperty()
	{
		Path path1 = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "src",
				"src.properties");
		System.out.println(path1.toAbsolutePath().toString());
	}

}
