package com.gson.test;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.gson.resource.Department;
import com.gson.resource.Employee;

/**
 * 4) Writing an Instance Creator: In most of the cases, Gson library is smart
 * enough to create instances even if any class does not provide default no-args
 * constructor. But, if you found any problem using a class having no no-args
 * constructor, you can use InstanceCreator support. You need to register the
 * InstanceCreator of a java class type with Gson first before using it.
 * 
 * @author Administrator
 * 
 */
public class GsonDemo2 {

	public static void main(String[] args) {

		// Now <strong>use the above InstanceCreator</strong> as below

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Department.class,
				new DepartmentInstanceCreator());
		Gson gson = gsonBuilder.create();

		System.out
				.println(gson
						.fromJson(
								"{'id':1,'firstName':'Lokesh','lastName':'Gupta','roles':['ADMIN','MANAGER'],'department':{'deptName':'Finance'}}",
								Employee.class));
	}
}

class DepartmentInstanceCreator implements InstanceCreator<Department> {
	public Department createInstance(Type type) {
		return new Department("None");
	}
}
