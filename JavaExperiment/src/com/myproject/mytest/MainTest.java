package com.myproject.mytest;

public class MainTest {

	public static void main(String[] args) {

		
		MainTest1 tMainTest1 = new MainTest1("test");
		tMainTest1.print();
		
		Boolean nullBool = null;
		String nullString = null;
		  
		boolean works = Boolean.valueOf( null );
		boolean alsoworks = Boolean.valueOf( nullString );
		boolean npe = Boolean.valueOf( nullBool );
		
		System.out.println("works -->" + works);
		System.out.println("alsoworks -->" + alsoworks);
		System.out.println("npe -->" + npe);
	}

}
