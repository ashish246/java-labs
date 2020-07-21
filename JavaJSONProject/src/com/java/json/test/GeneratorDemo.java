package com.java.json.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

/**
 * This jar (javax.json-1.0.jar) will be included in Java 7 & later versions and
 * will be part of Java 8.
 * 
 * @author Administrator
 * 
 */
public class GeneratorDemo {

	public static void main(String[] args) {

		Map<String, Object> properties = new HashMap<String, Object>(1);

		properties.put(JsonGenerator.PRETTY_PRINTING, true);

		JsonGeneratorFactory jgf = Json.createGeneratorFactory(properties);

		OutputStream os = null;
		try {
			os = new FileOutputStream("src/com/java/json/data/sample.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonGenerator jg = jgf.createGenerator(os);

		jg.writeStartObject() // {
				.write("name", "Jane Doe") // "name":"Jane Doe",
				.writeStartObject("address") // "address":{
				.write("type", 1) // "type":1,
				.write("street", "1 A Street") // "street":"1 A Street",
				.writeNull("city") // "city":null,
				.write("verified", false) // "verified":false
				.writeEnd() // },
				.writeStartArray("phone-numbers") // "phone-numbers":[
				.writeStartObject() // {
				.write("number", "555-1111") // "number":"555-1111",
				.write("extension", "123") // "extension":"123"
				.writeEnd() // },
				.writeStartObject() // {
				.write("number", "555-2222") // "number":"555-2222",
				.writeNull("extension") // "extension":null
				.writeEnd() // }
				.writeEnd() // ]
				.writeEnd() // }
				.close();

		// Using JsonArrayBuilder and JsonObjectBuilder
		JsonObjectBuilder customerObjectBuilder = Json.createObjectBuilder();

		customerObjectBuilder.add("name", "Ashish");

		JsonObjectBuilder addressObjectBuilder = Json.createObjectBuilder();
		addressObjectBuilder.add("street", "Beckett St");
		addressObjectBuilder.add("type", 1);
		addressObjectBuilder.add("verified", true);
		addressObjectBuilder.addNull("city");

		customerObjectBuilder.add("address", addressObjectBuilder);

		JsonArrayBuilder customersArrayBuilder = Json.createArrayBuilder();

		JsonObjectBuilder phoneObjectBuilder = Json.createObjectBuilder();
		phoneObjectBuilder.add("number", "123456789");
		phoneObjectBuilder.addNull("extension");
		customersArrayBuilder.add(phoneObjectBuilder);

		JsonObjectBuilder phoneObjectBuilder1 = Json.createObjectBuilder();
		phoneObjectBuilder1.add("number", "99999999999");
		phoneObjectBuilder1.addNull("extension");
		customersArrayBuilder.add(phoneObjectBuilder1);

		customerObjectBuilder.add("phoneNumbers", customersArrayBuilder);

		OutputStream os1 = null;
		try {
			os1 = new FileOutputStream("src/com/java/json/data/ashish.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonWriter writer = Json.createWriter(os1);
		writer.writeObject(customerObjectBuilder.build());
		
		writer.close();
	}

}
