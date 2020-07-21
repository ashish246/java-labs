package com.jackson.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.jackson.resources.Employee;

public class JavaToJSONExample {
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		Employee employee = new Employee(1, "Lokesh", "Gupta", new Date(1981,
				8, 18));
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(
					new File("src/com/jackson/data/employee.json"), employee);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}