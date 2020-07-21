package com.xmlparser.stax;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.stream.XMLStreamException;

public class StaxParserXMLUpdateDemo_2 {

	public static void main(String[] args) throws XMLStreamException,
			URISyntaxException, IOException {

		Path tSourceFile = Paths
				.get("src/com/xmlparser/data/PriceLists_Test_1.xml");


		XMLParserHelper tXMLHelper = new XMLParserHelper();
		tXMLHelper.processPriceListXML(tSourceFile);
		
		 /*Path tTargetOrgImportFile = Paths.get(tSourceFile.getParent().toString(), tSourceFile.getFileName().toString()
                 .replace(".xml", "").concat("_org.xml"));
		 
		 Files.move(tSourceFile, tTargetOrgImportFile);*/
		
	}

}
