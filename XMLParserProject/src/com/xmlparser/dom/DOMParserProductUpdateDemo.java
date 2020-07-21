package com.xmlparser.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParserProductUpdateDemo {

	public static void main(String argv[]) {

		try {
			String filepath = "src/com/xmlparser/data/ShippingMethods.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			docFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			NodeList totalCAList = doc.getElementsByTagName("shipping-charge-plan");
			
			//System.out.println("totalCAList --->" + new Integer(totalCAList.getLength()).toString());
			for (int n = 0; n < totalCAList.getLength(); n++) {
				Node shippingCharge = totalCAList.item(n);
				
				NodeList bracketList = shippingCharge.getChildNodes();
				
				//System.out.println("bracketList --->" + new Integer(bracketList.getLength()).toString());
				for (int k = 0; k < bracketList.getLength(); k++) {
					Node bracket = bracketList.item(k);
					
					if(bracket != null && bracket.getNodeName() != null && bracket.getNodeName().equals("bracket")){
						
						NodeList bracketChildList = bracket.getChildNodes();
						//System.out.println("bracketChildList --->" + new Integer(bracketChildList.getLength()).toString());
						for (int m = 0; m < bracketChildList.getLength(); m++) {
							Node bracketChild = bracketChildList.item(m);
							
							//System.out.println("bracketChild --->" + bracketChild.getNodeName());
							if(bracketChild != null && bracketChild.getNodeName() != null 
									&& (bracketChild.getNodeName().equals("currency") && bracketChild.getTextContent().equals("EUR"))){
								shippingCharge.removeChild(bracket);
							}
							
						}
					}
				}
			}
			
NodeList CAs = doc.getElementsByTagName("custom-attributes");
			
			for (int n = 0; n < CAs.getLength(); n++) {
				Node globalCA = CAs.item(n);
				NodeList caList = globalCA.getChildNodes();
				for (int k = 0; k < caList.getLength(); k++) {
					Node custom = caList.item(k);
					NamedNodeMap priceAttr = custom.getAttributes();
					if (priceAttr != null
							&& priceAttr.getNamedItem("name") != null
							&& priceAttr.getNamedItem("name").getNodeValue() != null
							&& priceAttr.getNamedItem("name").getNodeValue()
									.equals("AboveBracketCharge_EUR")) {
						
						globalCA.removeChild(custom);
					}
				}
			}
			
			
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
}
