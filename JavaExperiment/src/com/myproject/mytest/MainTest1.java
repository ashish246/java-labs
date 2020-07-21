package com.myproject.mytest;

public class MainTest1 {
	
	public String test1;
	
	public String test2;
	
	public MainTest1(final String pTest1){
	
		this(pTest1, null);
	}
	
	public MainTest1(final String pTest1, final String pTest2){
	
		test1= pTest1;
		test2 = pTest2;
	}
	
	public void print(){
		System.out.println("test1---->"+test1);
		System.out.println("test2---->"+test2);
	}

}
