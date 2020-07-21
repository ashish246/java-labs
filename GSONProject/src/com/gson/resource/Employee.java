package com.gson.resource;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;

/**
 * Google Gson is a Java library that can be used to convert Java Objects into
 * respective JSON format. In another way, it can used to convert the JSON into
 * equivalent java objects. There are some other java libraries also capable of
 * doing this conversion, but Gson stands among very few which do not require
 * any pre-annotated java classes OR sourcecode of java classes in any way
 * 
 * @author Administrator
 * 
 */
public class Employee {
	
	@Since(1.0)
	private Integer id;
	@Expose
	@SerializedName("first_name")
	private String firstName;
	@Expose
	private String lastName;
	
	@Since(1.1)
	private List<String> roles;
	@Since(1.1)
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