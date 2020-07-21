package com.jettison.namespace.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;

public class NamespaceUnmarshalDemo {

	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Customer.class);

		// Path tSourceFile =
		// Paths.get(ClassLoader.getSystemResource("src/com/jaxb/json/data/customer.json").toURI());

		File tFile = new File(
				"src/com/jettison/namespace/test/customer_namespace.json");

		JSONObject obj = new JSONObject(convertFileToString(tFile));

		Configuration config = new Configuration();

		Map<String, String> xmlToJsonNamespaces = new HashMap<String, String>(1);
		xmlToJsonNamespaces.put("http://www.example.org/package", "");
		xmlToJsonNamespaces.put("http://www.example.org/property", "prop");
		config.setXmlToJsonNamespaces(xmlToJsonNamespaces);

		MappedNamespaceConvention con = new MappedNamespaceConvention(config);
		XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Customer customer = (Customer) unmarshaller.unmarshal(xmlStreamReader);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		OutputStream os = new FileOutputStream(
				"src/com/jettison/namespace/test/customer_1.xml");
		marshaller.marshal(customer, os);
	}

	// Convert my file to a Base64 String
	private static String convertFileToString(File file) throws IOException {
		byte[] bytes = Files.readAllBytes(file.toPath());
		String encoded = DatatypeConverter.printBase64Binary(bytes);
		return new String(DatatypeConverter.parseBase64Binary(encoded));
	}

}
