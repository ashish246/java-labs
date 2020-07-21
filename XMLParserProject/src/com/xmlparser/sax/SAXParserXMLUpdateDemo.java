package com.xmlparser.sax;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserXMLUpdateDemo {

	public static void main(String[] args) throws Exception {

		/*
		 * http://stackoverflow.com/questions/4046932/how-to-change-values-of-some
		 * -elements-and-attributes-in-an-xml-file-java
		 */
		Path tSourceFile = Paths.get(ClassLoader.getSystemResource(
				"com/xmlparser/data/PriceLists_Test.xml").toURI());

		// Start STaX
		OutputStream out = new FileOutputStream("data.xml");
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(out);

		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		SAXUpdateHandler handler = new SAXUpdateHandler(writer);
		parser.parse(
				ClassLoader
						.getSystemResourceAsStream("com/xmlparser/data/PriceLists_Test.xml"),
				handler);

		// Printing the list of employees obtained from XML
		/*
		 * for (Employee emp : handler.empList) { System.out.println(emp); }
		 */
	}
}

/**
 * The Handler for SAX Events.
 */
class SAXUpdateHandler extends DefaultHandler {

	List<Employee> empList = new ArrayList<>();
	Employee emp = null;
	String content = null;
	AttributesImpl tAttributeImpl = null;
	XMLStreamWriter writer;

	public SAXUpdateHandler() {

	}

	public SAXUpdateHandler(XMLStreamWriter pWriter) {
		writer = pWriter;
	}

	@Override
	// Triggered when the start of tag is found.
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		try {
			writer.writeStartElement(uri, localName);
			for (int i = 0; i < attributes.getLength(); i++) {
				if (localName.equals("fixed-price-entry")
						&& attributes.getQName(i).toString().equals("quantity")) {

					writer.writeAttribute(attributes.getQName(i), "88888");
				} else {
					writer.writeAttribute(attributes.getQName(i),
							attributes.getValue(i));
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		try {
			writer.writeEndElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
