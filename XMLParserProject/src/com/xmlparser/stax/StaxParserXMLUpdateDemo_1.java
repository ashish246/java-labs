package com.xmlparser.stax;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

public class StaxParserXMLUpdateDemo_1 {

	static boolean isFPEStarted = false;
	static boolean isFPEEnded = true;
	public static XMLEventWriter writer;
	public static XMLStreamWriter streamWriter;
	public static XMLEventFactory eventFactory;

	public static void main(String[] args) throws XMLStreamException,
			URISyntaxException, IOException {

		/*
		 * Path tSourceFile = Paths.get(ClassLoader.getSystemResource(
		 * "com/xmlparser/data/PriceLists_Test.xml").toURI());
		 */

		Path tSourceFile = Paths
				.get("src/com/xmlparser/data/PriceLists_Test_1.xml");

		String tNewFileName = tSourceFile.getFileName().toString()
				.replace(".xml", "").concat("_original.xml");
		String tTargetFolder = tSourceFile.getParent().toString();
		Path tTargetPath = Paths.get(tTargetFolder, tNewFileName);
		/*
		 * try { Files.copy(tSourceFile, tTargetPath,
		 * StandardCopyOption.REPLACE_EXISTING); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		XMLInputFactory factory = XMLInputFactory.newInstance();

		XMLStreamReader streamReader = factory
				.createXMLStreamReader(new FileInputStream(tSourceFile.toFile()));

		XMLEventReader eventReader = factory
				.createXMLEventReader(new FileInputStream(tSourceFile.toFile()));

		/*
		 * XMLEventReader eventReader = factory.createFilteredReader(reader, new
		 * MyStreamFilter());
		 */

		XMLOutputFactory tOFactory = XMLOutputFactory.newInstance();
		writer = tOFactory.createXMLEventWriter(new FileWriter(tTargetPath
				.toFile()));

		streamWriter = tOFactory.createXMLStreamWriter(new FileWriter(
				tTargetPath.toFile()));

		eventFactory = XMLEventFactory.newInstance();

		useStreamReader(streamReader);
		 //useEventReader(eventReader);

	}

	public static void useEventReader(XMLEventReader eventReader)
			throws XMLStreamException {
		while (eventReader.hasNext()) {

			XMLEvent event = eventReader.nextEvent();
			
			if(event.getEventType() == XMLEvent.ENTITY_REFERENCE){
				isFPEEnded = false;
			}

			if (event.getEventType() == XMLEvent.START_ELEMENT) {
				

				if (event.asStartElement().getName().getLocalPart()
						.equalsIgnoreCase("fixed-price-entry")) {
					isFPEStarted = true;
					isFPEEnded = false;

					Iterator<Attribute> itr = event.asStartElement().getAttributes();
					XMLEvent updatedEvent = event;

					while (itr.hasNext()) {
						Attribute attr = (Attribute) itr.next();
						if (attr.getName().getLocalPart().equals("quantity")
								&& isFPEStarted && !isFPEEnded) {

							updatedEvent = eventFactory.createAttribute(attr
									.getName().toString(), "88888");

						}
					}
					
					writer.add(event);

				} 
				else {
					writer.add(event);
				}
			}

			else if (event.getEventType() == XMLEvent.END_ELEMENT) {

				writer.add(event);
				if (event.asEndElement().getName().getLocalPart()
						.equalsIgnoreCase("fixed-price-entry")) {

					isFPEEnded = true;
					isFPEStarted = false;

				}
			} 
			
			else {
				writer.add(event);
			}
		}

		writer.close();
	}

	public static void useStreamReader(XMLStreamReader streamReader)
			throws XMLStreamException {

		streamWriter.writeStartDocument();

		while (streamReader.hasNext()) {

			int event = streamReader.next();

			if (event == XMLEvent.ATTRIBUTE) {
				boolean isAttr = true;
			}

			if (event == XMLEvent.START_ELEMENT) {

				streamWriter.writeStartElement(streamReader.getName()
						.getLocalPart());

				if (streamReader.getName().getLocalPart()
						.equalsIgnoreCase("enfinity")) {

					int namespaceCount = streamReader.getNamespaceCount();
					for (int i = 0; i < namespaceCount; i++) {

						String prefix = streamReader.getNamespacePrefix(i);
						String uri = streamReader.getNamespaceURI(i);
						streamWriter.writeNamespace(prefix, uri);
					}

					// streamWriter.writeNamespace(prefix, namespaceURI);
				}

				int AttrCount = streamReader.getAttributeCount();
				String attrWtValue = null;
				for (int i = 0; i < AttrCount; i++) {
					String attrName = streamReader.getAttributeName(i)
							.getLocalPart();
					
					
					if(streamReader.getName().getLocalPart()
							.equalsIgnoreCase("fixed-price-entry")
							&& attrName.equals("weightTranslation")){
						
						attrWtValue = streamReader.getAttributeValue(i);
						
					}

					else if (streamReader.getName().getLocalPart()
							.equalsIgnoreCase("fixed-price-entry")
							&& attrName.equals("quantity")) {
						isFPEStarted = true;
						isFPEEnded = false;

						if(attrWtValue == null){
							attrWtValue = streamReader.getAttributeValue(i);
						}

						streamWriter.writeAttribute(attrName, attrWtValue);
					} else {
						if (streamReader.getAttributePrefix(i) == null) {
							streamWriter.writeAttribute(attrName,
									streamReader.getAttributeValue(i));
						} else {
							streamWriter.writeAttribute(attrName,
									streamReader.getAttributeValue(i));
						}
					}
				}

			}

			else if (event == XMLEvent.CHARACTERS) {
				streamWriter.writeCharacters(streamReader.getText());
			}

			else if (event == XMLEvent.END_ELEMENT) {

				streamWriter.writeEndElement();
				if (streamReader.getName().getLocalPart()
						.equalsIgnoreCase("fixed-price-entry")) {

					isFPEEnded = true;
					isFPEStarted = false;

				}
			} else {
				// streamWriter.add(event);
			}
		}

		streamWriter.close();
	}
}

class MyStreamFilter implements EventFilter {

	@Override
	public boolean accept(XMLEvent event) {

		if (event.getEventType() == XMLEvent.START_ELEMENT) {

			if (event.asStartElement().getName().getLocalPart()
					.equalsIgnoreCase("fixed-price-entry")) {

				return true;
			}
		}
		return false;
	}
}
