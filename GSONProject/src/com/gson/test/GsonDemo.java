package com.gson.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gson.resource.Employee;

/**
 * @author Administrator
 * 
 */
public class GsonDemo {

	public static void main(String[] args) {

		//marshal();

		unMarshal();
	}

	private static void marshal() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("Ashish");
		employee.setLastName("Tripathi");
		employee.setRoles(Arrays.asList("ADMIN", "MANAGER"));

		Gson gson = new Gson();

		try {
			OutputStream os = new FileOutputStream(
					"src/com/gson/data/employee.json");

			byte[] contentInBytes = gson.toJson(employee).getBytes();

			os.write(contentInBytes);
			os.flush();
			os.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(gson.toJson(employee));
	}

	private static void unMarshal() {
		Gson gson = new Gson();

		File tFile = new File("src/com/gson/data/employee.json");

		Employee employee = null;
		try {
			employee = gson
					.fromJson(convertFileToString(tFile), Employee.class);
		} catch (JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(employee.toString());
	}

	// Convert my file to a Base64 String
	private static String convertFileToString(File file) throws IOException {
		byte[] bytes = Files.readAllBytes(file.toPath());
		String encoded = DatatypeConverter.printBase64Binary(bytes);
		return new String(DatatypeConverter.parseBase64Binary(encoded));
	}

}
