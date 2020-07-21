package com.myproject.cloning;

public class Department implements Cloneable  {
	private int id;
	private String name;

	public Department(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	// Accessor/mutators methods will go there

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
}
