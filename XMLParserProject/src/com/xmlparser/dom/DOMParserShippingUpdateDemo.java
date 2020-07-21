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

public class DOMParserShippingUpdateDemo {

	public static void main(String argv[]) {

		try {
			String filepath = "src/com/xmlparser/data/SharedProducts.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			docFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the staff element by tag name directly
			NodeList offerList = doc.getElementsByTagName("offer");

			for (int i = 0; i < offerList.getLength(); i++) {

				Node offer = offerList.item(i);

				NodeList imageList = offer.getChildNodes();

				for (int j = 0; j < imageList.getLength(); j++) {
					Node child = imageList.item(j);

					//remove images
					if ("images".equals(child.getNodeName())) {
						offer.removeChild(child);
					}

					//remove de-DE elements
					NamedNodeMap attr = child.getAttributes();
					if (attr != null
							&& attr.getNamedItem("xml:lang") != null
							&& attr.getNamedItem("xml:lang").getNodeValue() != null
							&& attr.getNamedItem("xml:lang").getNodeValue()
									.equals("de-DE")) {
						
						offer.removeChild(child);
					}
					
					//remove de-DE custom attributes
					if ("custom-attributes".equals(child.getNodeName())) {
						NodeList caList = child.getChildNodes();
						
						for (int k = 0; k < caList.getLength(); k++) {
							Node custom = caList.item(k);
							NamedNodeMap customAttr = custom.getAttributes();
							if (customAttr != null
									&& customAttr.getNamedItem("xml:lang") != null
									&& customAttr.getNamedItem("xml:lang").getNodeValue() != null
									&& customAttr.getNamedItem("xml:lang").getNodeValue()
											.equals("de-DE")) {
								
								child.removeChild(custom);
							}
						}
					}
					
					//remove EUR prices
					if ("product-list-prices".equals(child.getNodeName())) {
						NodeList priceList = child.getChildNodes();
						
						for (int m = 0; m < priceList.getLength(); m++) {
							Node price = priceList.item(m);
							NamedNodeMap priceAttr = price.getAttributes();
							if (priceAttr != null
									&& priceAttr.getNamedItem("currency") != null
									&& priceAttr.getNamedItem("currency").getNodeValue() != null
									&& priceAttr.getNamedItem("currency").getNodeValue()
											.equals("EUR")) {
								
								child.removeChild(price);
							}
						}
					}
					
				}
			}
			
			NodeList totalCAList = doc.getElementsByTagName("custom-attributes");
			
			for (int n = 0; n < totalCAList.getLength(); n++) {
				Node globalCA = totalCAList.item(n);
				NodeList caList = globalCA.getChildNodes();
				for (int k = 0; k < caList.getLength(); k++) {
					Node custom = caList.item(k);
					NamedNodeMap customAttr = custom.getAttributes();
					if (customAttr != null
							&& customAttr.getNamedItem("xml:lang") != null
							&& customAttr.getNamedItem("xml:lang").getNodeValue() != null
							&& customAttr.getNamedItem("xml:lang").getNodeValue()
							.equals("de-DE")) {
						
						globalCA.removeChild(custom);
					}
				}
			}
			
			

			NodeList totalWarrantyList = doc.getElementsByTagName("warranty-prices");
			
			for (int n = 0; n < totalWarrantyList.getLength(); n++) {
				Node globalCA = totalWarrantyList.item(n);
				NodeList caList = globalCA.getChildNodes();
				for (int k = 0; k < caList.getLength(); k++) {
					Node custom = caList.item(k);
					NamedNodeMap priceAttr = custom.getAttributes();
					if (priceAttr != null
							&& priceAttr.getNamedItem("currency") != null
							&& priceAttr.getNamedItem("currency").getNodeValue() != null
							&& priceAttr.getNamedItem("currency").getNodeValue()
									.equals("EUR")) {
						
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
