package com.collection.set;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample6
{
	public static void main(String args[])
	{
		try
		{
			String elements[] = {"Huda Tutorials","Java Tutorials","Android Tutorials"};

			//java.util.TreeSet DECLARATION
			Set s;

			//java.util.Set OBJECT CREATION USING ARRAY AS LIST
			//USING DEFAULT CONSTRUCTOR
			s = new TreeSet(Arrays.asList(elements));

			//FileOutputStream CREATION
			FileOutputStream fos = new FileOutputStream("set.set");

			//ObjectOutputStream CREATION
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			//WRITE Set OBJECT TO ObjectOutputStream
			oos.writeObject(s);

			//CLOSE THE ObjectOutputStream
			oos.close();
			System.out.println("Set Collection Saved into File Sucessfully");
		}
		catch(Exception e)
		{
			System.out.println("Error Occurred : " + e.getMessage());
		}
	}
}