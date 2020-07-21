package com.jaxbproject.binder;

import java.io.File;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * The Binder interface was introduced in JAXB 2.0, yet many developers are not
 * familiar with this JAXB feature. It is created from the JAXBContext, and
 * interacts with XML in the form of a DOM. The Binder maintains an association
 * between the Java objects and their corresponding DOM nodes.
 * 
 * Instead of creating a new XML document, Binder applies the changes made to
 * the object back to the original DOM. This is how JAXB is able to preserve the
 * XML infoset.
 * 
 * There appears to be a bug in the Metro version of JAXB regarding this
 * example. You will need to ensure you use EclipseLink JAXB (MOXy). To use the
 * MOXy implementation you will need to add a file named jaxb.properties in with
 * your model classes that contains the following entry:
 * 
 * javax.xml.bind.context.factory=org.eclipse.persistence.jaxb.JAXBContextFactory
 * 
 * @author Administrator
 * 
 */
public class BinderDemo {

	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		File xml = new File("input.xml");
		Document document = db.parse(xml);

		JAXBContext jc = JAXBContext.newInstance(Customer.class);

		Binder<Node> binder = jc.createBinder();
		Customer customer = (Customer) binder.unmarshal(document);
		customer.getAddress().setStreet("2 NEW STREET");
		PhoneNumber workPhone = new PhoneNumber();
		workPhone.setType("work");
		workPhone.setValue("555-WORK");
		customer.getPhoneNumbers().add(workPhone);
		binder.updateXML(customer);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.transform(new DOMSource(document), new StreamResult(System.out));
	}

}
