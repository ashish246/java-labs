package com.xmlparser.sax;

import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXUpdateDemo2 {

	public static void main(String[] args) throws Exception {
	    String xml = "<users id='111'><user><name>user1</name></user></users>";
	    Path tSourceFile = Paths.get(ClassLoader.getSystemResource(
				"com/xmlparser/data/PriceLists_Test.xml").toURI());
	    
	    XMLReader xr = new XMLFilterImpl(XMLReaderFactory.createXMLReader()) {
	        private String tagName = "";
	        @Override
	        public void startElement(String uri, String localName, String qName, Attributes attributes)
	                throws SAXException {
	            tagName = qName;
	            
	            for (int i = 0; i < attributes.getLength(); i++) {
					if (localName.equals("users")
							&& attributes.getQName(i).toString().equals("id")) {
						AttributesImpl tAttributeImpl = new AttributesImpl(
								attributes);

						tAttributeImpl.setValue(i, "888888");
						
					} else {
						
					}
				}
	            
	            super.startElement(uri, localName, qName, attributes);
	        }
	        public void endElement(String uri, String localName, String qName) throws SAXException {
	            tagName = "";
	            super.endElement(uri, localName, qName);
	        }
	        @Override
	        public void characters(char[] ch, int start, int length) throws SAXException {
	            if (tagName.equals("name")) {
	                ch = "user2".toCharArray();
	                start = 0;
	                length = ch.length; 
	            }
	            super.characters(ch, start, length);
	        }
	    };
	    Source src = new SAXSource(xr, new InputSource(new StringReader(xml)));
	    Result res = new StreamResult(System.out);
	    TransformerFactory.newInstance().newTransformer().transform(src, res);
	}
	
}
