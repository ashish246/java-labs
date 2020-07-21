@XmlSchema(namespace = "http://www.auspost.com.au/Schema/CommonMessageModel/ActivityEvent:v4", elementFormDefault = XmlNsForm.QUALIFIED,
//stating elementFormDefault="qualified" means that the targetNamespace applies to each element unless overridden by putting form="unqualified" on the element declaration.
xmlns = {
		@XmlNs(prefix = "pfx9", namespaceURI = "http://www.auspost.com.au/Schema/CommonMessageModel/ActivityEvent:v4"),
		@XmlNs(prefix = "ns32", namespaceURI = "http://www.auspost.com.au/Schema/CommonDataModel/Product:v1"),
		@XmlNs(prefix = "pfx12", namespaceURI = "http://schemas.xmlsoap.org/soap/envelope/"),
		@XmlNs(prefix = "pfx7", namespaceURI = "http://www.auspost.com.au/Schema/CommonDataModel/Common:v1"),
		@XmlNs(prefix = "ns3", namespaceURI = "http://www.auspost.com.au/Schema/CommonDataModel/InvolvedParty/Customer:v1"),
		@XmlNs(prefix = "ns5", namespaceURI = "http://www.auspost.com.au/Schema/CommonDataModel/Location:v1"),
		@XmlNs(prefix = "ns23", namespaceURI = "http://www.auspost.com.au/ESB/schema/ESBTypes"),
		@XmlNs(prefix = "ns22", namespaceURI = "http://www.auspost.com.au/Schema/CommonDataModel/Event:v2"),
		@XmlNs(prefix = "ns4", namespaceURI = "http://www.auspost.com.au/Schema/CommonDataModel/InvolvedParty:v1"),
		@XmlNs(prefix = "ns1", namespaceURI = "http://www.tibco.com/pe/WriteToLogActivitySchema"),
		@XmlNs(prefix = "tib", namespaceURI = "http://www.tibco.com/bw/xslt/custom-functions"),
		@XmlNs(prefix = "pfx", namespaceURI = "http://www.auspost.com.au/xml/pcms"),

		@XmlNs(prefix = "xsd", namespaceURI = "http://www.w3.org/2001/XMLSchema"),

		@XmlNs(prefix = "ns", namespaceURI = "http://www.tibco.com/pe/DeployedVarsType"),
		@XmlNs(prefix = "ns2", namespaceURI = "http://www.auspost.com.au/Schema/CommonDataModel/Mail_Product:v2"),
		@XmlNs(prefix = "ns12", namespaceURI = "http://www.tibco.com/pe/GenerateErrorActivity/InputSchema"),
		@XmlNs(prefix = "pd", namespaceURI = "http://xmlns.tibco.com/bw/process/2003"),
		@XmlNs(prefix = "ns6", namespaceURI = "http://www.tibco.com/pe/EngineTypes") })
package com.jaxbproject.export;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

