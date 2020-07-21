package com.jackson.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

import com.jackson.resources.Employee;

public class JSONWithFilters {
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		Employee employee = new Employee(1, "Lokesh", "Gupta", new Date(1981,
				8, 18));
		ObjectMapper mapper = new ObjectMapper();

		// first, construct filter provider to exclude all properties but
		// 'name', bind it as 'myFilter'
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				"myFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
						"firstName", "lastName"));

		try {
			mapper.filteredWriter(filters).writeValue(
					new File("src/com/jackson/data/employee_filter.json"),
					employee);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
