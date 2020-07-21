package com.xmlparser.stax;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;


/**
 * @author Ashish Tripathi
 * 
 */
public class XMLParserHelper {

	private XMLStreamReader mStreamReader;
	private XMLStreamWriter mStreamWriter;
	private XMLInputFactory mInputFactory;
	private XMLOutputFactory mOutputFactory;

	private Path mUpdatedImportFile;

	InputStream mFileInputStream;
	OutputStream mFileOutputStream;

	public XMLParserHelper() {
		mInputFactory = XMLInputFactory.newInstance();
		mOutputFactory = XMLOutputFactory.newInstance();
		mOutputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES,
				true);
	}

	public void processPriceListXML(Path pImportFile)
			throws XMLStreamException, IOException {
		Path mImportFile = pImportFile;

		mUpdatedImportFile = Paths.get(mImportFile.getParent().toString(),
				mImportFile.getFileName().toString().replace(".xml", "")
						.concat("_mod.xml"));

		mFileInputStream = new FileInputStream(mImportFile.toFile());
		mStreamReader = mInputFactory.createXMLStreamReader(mFileInputStream);

		mFileOutputStream = new FileOutputStream(mUpdatedImportFile.toFile());
		mStreamWriter = mOutputFactory.createXMLStreamWriter(mFileOutputStream,
				"UTF-8");

		updateFixedPriceQuantity();
	}

	public void updateFixedPriceQuantity() throws XMLStreamException,
			IOException {

		mStreamWriter.writeStartDocument("UTF-8", "1.0");

		String tProductSKU = null;
		while (mStreamReader.hasNext()) {
			int event = mStreamReader.next();

			if (event == XMLStreamConstants.START_ELEMENT) {
				mStreamWriter.writeStartElement(mStreamReader.getName()
						.getLocalPart());

				if (mStreamReader.getName().getLocalPart()
						.equalsIgnoreCase("enfinity")) {
					int namespaceCount = mStreamReader.getNamespaceCount();
					for (int i = 0; i < namespaceCount; i++) {
						String prefix = mStreamReader.getNamespacePrefix(i);
						String uri = mStreamReader.getNamespaceURI(i);
						mStreamWriter.writeNamespace(prefix, uri);
					}

					// mStreamWriter.writeNamespace("xml",
					// XMLConstants.XML_NS_URI);

					/*
					 * int AttrCount = mStreamReader.getAttributeCount(); for
					 * (int i = 0; i < AttrCount; i++) { QName attrName =
					 * mStreamReader.getAttributeName(i);
					 * 
					 * if (attrName.getPrefix() == null) {
					 * mStreamWriter.writeAttribute(attrName.getLocalPart(),
					 * mStreamReader.getAttributeValue(i)); } else {
					 * mStreamWriter.writeAttribute(attrName.getNamespaceURI(),
					 * attrName.getLocalPart(),
					 * mStreamReader.getAttributeValue(i)); } }
					 */
				}
				int AttrCount = mStreamReader.getAttributeCount();
				String tWeightCategoryValue = null;
				for (int i = 0; i < AttrCount; i++) {
					QName attrName = mStreamReader.getAttributeName(i);

					// get the product SKU value
					if (mStreamReader.getName().getLocalPart()
							.equals("product-price-list-entry")
							&& attrName.getLocalPart().equals("sku")) {
						tProductSKU = mStreamReader.getAttributeValue(i);
					}

					if (mStreamReader.getName().getLocalPart()
							.equalsIgnoreCase("fixed-price-entry")
							&& attrName.getLocalPart().equals(
									"weightTranslation")) {
						tWeightCategoryValue = mStreamReader
								.getAttributeValue(i);
					}

					else if (mStreamReader.getName().getLocalPart()
							.equalsIgnoreCase("fixed-price-entry")
							&& attrName.getLocalPart().equals("quantity")) {
						if (tWeightCategoryValue == null) {
							mStreamWriter.writeAttribute(
									attrName.getLocalPart(),
									mStreamReader.getAttributeValue(i));
						} else {
							String tWeightKey = mStreamReader
									.getAttributeValue(i);

							// TODO fetch the actual weight quantity using
							// tWeightKey & tWeightTranlsationValue
							String tActualQuantity = tWeightKey;

							System.out
									.println("Value of actual weight quantity for the weight key '"
											.concat(tWeightKey)
											.concat("' and weight translation category '")
											.concat(tWeightCategoryValue)
											.concat("' for the fixed price entry of the product SKU ")
											.concat(tProductSKU)
											.concat(" is found to be: ")
											.concat(tActualQuantity));
							mStreamWriter.writeAttribute(
									attrName.getLocalPart(), tActualQuantity);
						}
					} else {
						String prefix = attrName.getPrefix();
						if (prefix == null || prefix.equals("")) {
							mStreamWriter.writeAttribute(
									attrName.getLocalPart(),
									mStreamReader.getAttributeValue(i));
						} else {
							mStreamWriter.writeAttribute(
									attrName.getNamespaceURI(),
									attrName.getLocalPart(),
									mStreamReader.getAttributeValue(i));
						}
					}
				}
			}

			if (event == XMLStreamConstants.CHARACTERS) {
				mStreamWriter.writeCharacters(mStreamReader.getText());
			}

			if (event == XMLStreamConstants.END_ELEMENT) {
				if (mStreamReader.getName().getLocalPart()
						.equals("product-price-list-entry")) {
					tProductSKU = null;
				}
				mStreamWriter.writeEndElement();
			}
		}

		// close the open resources
		mStreamWriter.close();
		mFileInputStream.close();
		mFileOutputStream.close();
		mStreamWriter.close();
	}

	/**
	 * @return the mUpdatedImportFile
	 */
	public Path getUpdatedImportFile() {
		return mUpdatedImportFile;
	}

	/**
	 * @param mUpdatedImportFile
	 *            the mUpdatedImportFile to set
	 */
	public void setUpdatedImportFile(Path pUpdatedImportFile) {
		this.mUpdatedImportFile = pUpdatedImportFile;
	}
}
