package com.myproject.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * The text above has contained a lot of talk. Let's look at a simple example.
 * Below is an example of a simple ClassLoader subclass. Notice how it delegates
 * class loading to its parent except for the one class it is intended to be
 * able to reload. If the loading of this class is delegated to the parent class
 * loader, it cannot be reloaded later. Remember, a class can only be loaded
 * once by the same ClassLoader instance.
 * 
 * As said earlier, this is just an example that serves to show you the basics
 * of a ClassLoader's behaviour. It is not a production ready template for your
 * own class loaders. Your own class loaders should probably not be limited to a
 * single class, but a collection of classes that you know you will need to
 * reload. In addition, you should probably not hardcode the class paths either
 * 
 * @author Administrator
 * 
 */
public class MyClassLoader extends ClassLoader {

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}

	public Class loadClass(String name) throws ClassNotFoundException {
		if (!"com.myproject.classloader.MyObject".equals(name))
			return super.loadClass(name);

		try {
			String url = "file:E:/Projects/My_Projects/Shared/TechStuff/AT/Java/JavaExperiment/bin/com/myproject/classloader/MyObject.class";
			URL myUrl = new URL(url);
			URLConnection connection = myUrl.openConnection();
			InputStream input = connection.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int data = input.read();

			while (data != -1) {
				buffer.write(data);
				data = input.read();
			}

			input.close();

			byte[] classData = buffer.toByteArray();

			return defineClass("com.myproject.classloader.MyObject", classData,
					0, classData.length);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}