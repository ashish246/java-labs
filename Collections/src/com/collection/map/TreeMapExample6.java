package com.collection.map;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.TreeMap;

public class TreeMapExample6
{
	public static void main(String args[])
	{
		try
		{
			//java.util.TreeMap DECLARATION
			TreeMap <String,Integer> h;

			//FileInputStream CREATION
			FileInputStream fis = new FileInputStream("treemap.set");

			//ObjectInputStream CREATION
			ObjectInputStream ois = new ObjectInputStream(fis);

			//READ TreeMap OBJECT FROM ObjectInputStream
			h = (TreeMap) ois.readObject();
			ois.close();
			System.out.println(h);
		}
		catch(Exception e)
		{
			System.out.println("Error Occurred : " + e.getMessage());
		}
	}
}