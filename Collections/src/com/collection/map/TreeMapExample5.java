package com.collection.map;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

public class TreeMapExample5
{
	public static void main(String args[])
	{
		try
		{
			//java.util.TreeMap DECLARATION
			TreeMap <String,Integer> h;

			//java.util.TreeMap OBJECT CREATION
			//USING DEFAULT CONSTRUCTOR
			h = new TreeMap <String,Integer>();

			//ADD AN ELEMENTS
			h.put("ONE", new Integer(1));
			h.put("TWO", new Integer(2));
			h.put("THREE", new Integer(3));

			//FileOutputStream CREATION
			FileOutputStream fos = new FileOutputStream("treemap.set");

			//ObjectOutputStream CREATION
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			//WRITE Set OBJECT TO ObjectOutputStream
			oos.writeObject(h);

			//CLOSE THE ObjectOutputStream
			oos.close();
			System.out.println("TreeMap Saved into File Sucessfully");
		}
		catch(Exception e)
		{
			System.out.println("Error Occurred : " + e.getMessage());
		}
	}
}