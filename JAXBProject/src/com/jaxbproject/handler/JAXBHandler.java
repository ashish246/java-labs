package com.jaxbproject.handler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBHandler {

	public JAXBHandler() {

	}

	/**
	 * Parse any Object which is of type ClassObject into XML using JAXB
	 * 
	 * @param object
	 * @param classObject
	 * @return
	 */
	public static String parseObjectToXML(Object object, Class<?> classObject) {

		// Generate XML String here
		StringWriter tObjectXML = new StringWriter();

		try {
			JAXBContext jc = JAXBContext.newInstance(classObject);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

			OutputStream os = new FileOutputStream( "ManifextEnvelope.xml" );
			marshaller.marshal(object, os);
			
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return tObjectXML.toString();
	}
	
	public static Object generateObjectFromXML(){
		
		
		return null;
	}

}
