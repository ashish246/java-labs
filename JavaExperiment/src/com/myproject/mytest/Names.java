package com.myproject.mytest;

public class Names {

	public static void main(String[] args) {

		MainTest main = new MainTest();
		
		System.out.println("main Class name ->"+ main.getClass().getName());
		
		int[] inte = {5};
		
		System.out.println("int Class name ->"+ inte.getClass().getName());
		
		MainTest[] mainArr = {};
		
		System.out.println("Main array Class name ->"+ mainArr.getClass().getName());
	}

}
