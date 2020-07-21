package com.jettison.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import com.jettison.resource.Customer;

public class MarshalDemo {
	 
    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Customer.class);
  
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Customer customer = (Customer) unmarshaller.unmarshal(new File("src/com/jettison/data/input.xml"));
 
        Configuration config = new Configuration();
		MappedNamespaceConvention con = new MappedNamespaceConvention(config);
		
		
		OutputStream os = new FileOutputStream("src/com/jettison/data/customer.json");
        //Writer writer = new OutputStreamWriter(System.out);
		Writer writer = new OutputStreamWriter(os);
        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
 
        Marshaller marshaller = jc.createMarshaller();
        
		marshaller.marshal(customer, xmlStreamWriter);
		
        //marshaller.marshal(customer, xmlStreamWriter);
    }
 
}
