package com.jackson.resources;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonFilter;

/**
 * 
 * @author Administrator
 * 
 */
@JsonFilter("myFilter")
public class Employee {
	
	private Integer id;
	private String firstName;
	private String lastName;
	
	private List<String> roles;
	private Date birthDate;
	// added for Demo2
	private Department department;

	public Employee() {
	}

	public Employee(Integer id, String firstName, String lastName,
			Date birthDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", "
				+ "lastName=" + lastName + ", roles=" + roles + ", department="
				+ department + ", birthDate=" + birthDate + "]";
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}