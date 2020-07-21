package com.xerces.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Administrator
 *
 */
public class XercesDemo {

   // XML tag's
   private static final String TAG_PROBE_MSG      = "ProbeMsg";
   private static final String TAG_TIMESTAMP      = "TimeStamp";
   private static final String TAG_PROPE_ID       = "ProbeId";
   private static final String TAG_PROPE_VALUE    = "ProbeValue";

   // XML Settings
   private static final String XML_VERSION        = "1.0";
   private static final String XML_ENCODING       = "UTF-8";

   // Format definitions
   private static final String DATE_TIME_FORMAT   =
     "yyyy-MM-dd'T'HH:mm:ss'Z'";

   // Variables
   private Date     msgTimeStamp  = null;
   private String   probeId       = "";
   private Integer  probeValue    = null;
   private Document xmlDoc        = null;
   private String   xmlStr        = null;

    // Constructor
    public XercesDemo(Date   pTimeStamp
                  ,String pProbeId
                   ,int    pProbeValue ) {

      this.msgTimeStamp = pTimeStamp;
      this.probeId      = pProbeId;
      this.probeValue   = new Integer(pProbeValue);

     // Generate the XML Document using DOM
      this.generateXMLDocument();

      // Generate a XML String
      this.generateXMLString();
    }

    // Retrive probe message as XML string
   public String getXMLString() {
      return xmlStr;
    }

    // Generate a DOM XML document
    private void generateXMLDocument()
    {
     Element root;
     Element item;
      DateFormat timeStampFormat =
      new SimpleDateFormat( DATE_TIME_FORMAT );

    try {

      //Create a XML Document
      DocumentBuilderFactory dbFactory =
        DocumentBuilderFactoryImpl.newInstance();
      DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
      xmlDoc = docBuilder.newDocument();
    } catch(Exception e) {
        System.out.println("Error " + e);
    }

    // Create the root element
    root = xmlDoc.createElement(TAG_PROBE_MSG);

    // Add TimeStamp Element and its value
    item = xmlDoc.createElement(TAG_TIMESTAMP);
    item.appendChild(xmlDoc.createTextNode(
      timeStampFormat.format(msgTimeStamp)));
    root.appendChild(item);

    // Add ProbeId Element and its value
    item = xmlDoc.createElement(TAG_PROPE_ID);
    item.appendChild(xmlDoc.createTextNode(probeId));
    root.appendChild(item);

    // Add ProbeValue Element and its value
    item = xmlDoc.createElement(TAG_PROPE_VALUE);
    item.appendChild(xmlDoc.createTextNode(probeValue.toString() ));
    item.setAttribute("ScaleUnit", "mm");
    root.appendChild(item);

    // Add to the root Element
    xmlDoc.appendChild(root);

  }

  // Generate String out of the XML document object
  private void generateXMLString() {

    StringWriter  strWriter    = null;
    XMLSerializer probeMsgSerializer   = null;
    OutputFormat  outFormat    = null;

    try {
      probeMsgSerializer = new XMLSerializer();
      strWriter = new StringWriter();
      outFormat = new OutputFormat();

      // Setup format settings
      outFormat.setEncoding(XML_ENCODING);
      outFormat.setVersion(XML_VERSION);
      outFormat.setIndenting(true);
      outFormat.setIndent(4);

      // Define a Writer
      probeMsgSerializer.setOutputCharStream(strWriter);
      
      OutputStream os = new FileOutputStream("src/com/xerces/data/sample.xml");

      // Apply the format settings
      probeMsgSerializer.setOutputFormat(outFormat);

      probeMsgSerializer.setOutputByteStream(os);
      
      // Serialize XML Document
      probeMsgSerializer.serialize(xmlDoc);
      this.xmlStr = strWriter.toString();
      strWriter.close();

    } catch (IOException ioEx) {
        System.out.println("Error " + ioEx);
    }
  }

  public static void main (String argv[]) {

    XercesDemo pMsg = new XercesDemo(new Date()   // Timestamp
                                ,"1A6F"       // Probe ID
                                ,1245);       // Probe Value
    System.out.println(pMsg.getXMLString() );
  }
}
