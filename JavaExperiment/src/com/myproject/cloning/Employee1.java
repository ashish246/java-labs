package com.myproject.cloning;

public class Employee1 implements Cloneable {

	private int empoyeeId;
	private String employeeName;
	private Department department;

	public Employee1(int id, String name, Department dept) {
		this.setEmpoyeeId(id);
		this.setEmployeeName(name);
		this.setDepartment(dept);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
	    Employee1 cloned = (Employee1)super.clone();
	    cloned.setDepartment((Department)cloned.getDepartment().clone());
	    return cloned;
	}

	public int getEmpoyeeId() {
		return empoyeeId;
	}

	public void setEmpoyeeId(int empoyeeId) {
		this.empoyeeId = empoyeeId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	// Accessor/mutators methods will go there
}
