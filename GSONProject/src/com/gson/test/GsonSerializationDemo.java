package com.gson.test;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gson.api.DateDeserializer;
import com.gson.api.DateSerializer;
import com.gson.resource.Employee;

public class GsonSerializationDemo {

	public static void main(String[] args) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		
		//set pretty printing
		gsonBuilder.setPrettyPrinting();
		
		//set version
		gsonBuilder.setVersion(1.0);
		
		//exclude specific access modifier type
		gsonBuilder.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE);
		
		//exculde selected fields
		gsonBuilder.excludeFieldsWithoutExposeAnnotation();

		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("Lokesh");
		employee.setLastName("Gupta");
		employee.setRoles(Arrays.asList("ADMIN", "MANAGER"));
		employee.setBirthDate(new Date());

		Gson gson = gsonBuilder.create();

		// Convert to JSON
		System.out.println(gson.toJson(employee));

		// Convert to java objects
		System.out
				.println(gson
						.fromJson(
								"{'id':1,'firstName':'Lokesh','lastName':'Gupta','roles':['ADMIN','MANAGER'],'birthDate':'17/06/2014'}",
								Employee.class));

	}

}
