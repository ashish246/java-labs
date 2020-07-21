package com.xmlparser.stax;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

public class StaxParserXMLUpdateDemo {
	public static void main(String[] args) throws XMLStreamException,
			URISyntaxException, IOException {

		Path tSourceFile = Paths.get(ClassLoader.getSystemResource(
				"com/xmlparser/data/PriceLists_Test.xml").toURI());

		String tNewFileName = tSourceFile.getFileName().toString()
				.concat("_original");
		String tTargetFolder = tSourceFile.getParent().toString();
		Path tTargetPath = Paths.get(tTargetFolder, tNewFileName);
		/*
		 * try { Files.copy(tSourceFile, tTargetPath,
		 * StandardCopyOption.REPLACE_EXISTING); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader eventReader = factory
				.createXMLEventReader(new FileInputStream(tSourceFile.toFile()));

		XMLOutputFactory tOFactory = XMLOutputFactory.newInstance();
		XMLEventWriter writer = tOFactory.createXMLEventWriter(new FileWriter(
				tTargetPath.toFile()));

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();

		boolean hasFixedPriceEntryEnded = true;
		while (eventReader.hasNext()) {

			XMLEvent event = eventReader.nextEvent();
			writer.add(event);

			if (event.getEventType() == XMLEvent.START_ELEMENT) {
				/*
				 * writer.writeStartElement(event.asStartElement().getName()
				 * .toString());
				 */

				if (event.asStartElement().getName().getLocalPart()
						.equalsIgnoreCase("fixed-price-entry")) {
					hasFixedPriceEntryEnded = false;
					

					Iterator itr = event.asStartElement().getAttributes();
					while (itr.hasNext()) {
						Attribute attr = (Attribute) itr.next();
						if (attr.getName().getLocalPart().equals("quantity")) {

							writer.add(eventFactory.createAttribute(attr
									.getName().toString(), "88888"));
						}
					}
				}
			}
		}

		writer.close();
	}
}
