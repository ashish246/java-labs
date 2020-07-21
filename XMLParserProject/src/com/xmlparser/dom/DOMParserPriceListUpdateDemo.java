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

public class DOMParserPriceListUpdateDemo {

	public static void main(String argv[]) {

		try {
			String filepath = "src/com/xmlparser/data/PriceLists.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			docFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);


			NodeList totalWarrantyList = doc.getElementsByTagName("product-price-list-entry");
			
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
			
			String filepath1 = "src/com/xmlparser/data/PriceLists_x.xml";
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath1));
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
