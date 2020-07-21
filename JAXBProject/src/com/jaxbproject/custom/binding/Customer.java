package com.jaxbproject.custom.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import com.jaxbproject.binder.Address;
import com.jaxbproject.binder.PhoneNumber;

/**
 * This class is generated by the following command using custom jaxb binding
 * via XJC:
 * 
 * xjc -d out -p com.jaxbproject.custom.binding -b ExtJaxbBinding.jxb customer.xsd
 * 
 * @author Administrator
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customer", propOrder = { "name", "addressOrPhoneNumberOrNote" })
public class Customer {

	protected String name;

	@XmlElements({
			@XmlElement(name = "phone-number", type = PhoneNumber.class),
			@XmlElement(name = "address", type = Address.class),
			@XmlElement(name = "note", type = String.class) })
	protected Object addressOrPhoneNumberOrNote;

}
