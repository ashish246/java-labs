package com.xmlparser.stax;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class StaxXMLValidation {

	public static void main(String args[]) throws Exception {

		XMLStreamReader reader = XMLInputFactory.newInstance()
				.createXMLStreamReader(new FileInputStream("test.xml"));

		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new File("test.xsd"));

		Validator validator = schema.newValidator();
		validator.validate(new StAXSource(reader));

		// no exception thrown, so valid
		System.out.println("Document is valid");

	}

}
