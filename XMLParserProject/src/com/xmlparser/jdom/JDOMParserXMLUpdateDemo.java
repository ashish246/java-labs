package com.xmlparser.jdom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JDOMParserXMLUpdateDemo {
	public static void main(String[] args) {

		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("src/com/xmlparser/data/PriceLists_Test.xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			// update staff id attribute
			List<Element> tFPEList = rootNode.getChildren("product-price-list");//rootNode.getChild("fixed-price-entry");
			
			for(Element element: tFPEList){
				List<Element> tPPLEList = element.getChildren("product-price-list-entry ");
				
				for(Element element1: tPPLEList){
					Element priceScaleTable = element1.getChild("price-scale-table");
					
					if(priceScaleTable != null){
						Element priceScaleEntries = priceScaleTable.getChild("price-scale-entries");
						
						if(priceScaleEntries != null){
							List<Element> tFixedPriceList = priceScaleEntries.getChildren("fixed-price-entry");
							
							for(Element element2: tFixedPriceList){
								String oldQuantity = element2.getAttribute("quantity").getValue();
								
								if(element2.getChild("custom-attributes") != null){
									List<Element> CAList = element2.getChild("custom-attributes").getChildren("custom-attribute");
									
									for(Element element3: CAList){
										if(element3.getAttribute("name") != null && element3.getAttribute("name").getValue().equals("weightTranslation")){
											String translationFactor = element3.getText();
											
											element2.getAttribute("quantity").setValue(translationFactor);
										}
									}
								}
							}
						}
					}
				}
			}
				
			
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("src/com/xmlparser/data/PriceLists_Updated.xml"));

			// xmlOutput.output(doc, System.out);

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
}
