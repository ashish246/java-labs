package com.jaxbproject.handler;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.jaxbproject.generated.ExpenseT;
import com.jaxbproject.generated.ItemListT;
import com.jaxbproject.generated.ItemT;
import com.jaxbproject.generated.ObjectFactory;
import com.jaxbproject.generated.UserT;

public class XmlToJavaObjects {

	/**
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException {
		// 1. We need to create JAXContext instance
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

		// 2. Use JAXBContext instance to create the Unmarshaller.
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		//set schema
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = sf.newSchema(new File("src/expense.xsd"));

			unmarshaller.setSchema(schema);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//set validation event handler.
		unmarshaller.setEventHandler(new MyValidationEventHandler());

		// 3. Use the Unmarshaller to unmarshal the XML document to get an
		// instance of JAXBElement.
		JAXBElement<ExpenseT> unmarshalledObject = (JAXBElement<ExpenseT>) unmarshaller
				.unmarshal(ClassLoader
						.getSystemResourceAsStream("com/jaxbproject/handler/expense.xml"));

		// 4. Get the instance of the required JAXB Root Class from the
		// JAXBElement.
		ExpenseT expenseObj = unmarshalledObject.getValue();
		UserT user = expenseObj.getUser();
		ItemListT items = expenseObj.getItems();

		// Obtaining all the required data from the JAXB Root class instance.
		System.out.println("Printing the Expense for: " + user.getUserName());
		for (ItemT item : items.getItem()) {
			System.out.println("Name: " + item.getItemName());
			System.out.println("Value: " + item.getAmount());
			System.out.println("Date of Purchase: " + item.getPurchasedOn());
		}
	}

}