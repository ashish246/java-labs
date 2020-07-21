//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.12 at 10:17:53 AM EST 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for shipmentsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="shipmentsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customer_reference_1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customer_reference_2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="items" type="{}itemsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lodgement" type="{}lodgementType"/>
 *         &lt;element name="shipment_reference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destination" type="{}destinationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shipmentsType", propOrder = {
    "customerReference1",
    "customerReference2",
    "items",
    "lodgement",
    "shipmentReference",
    "destination"
})
public class ShipmentsType {

    @XmlElement(name = "customer_reference_1", required = true)
    protected String customerReference1;
    @XmlElement(name = "customer_reference_2", required = true)
    protected String customerReference2;
    protected List<ItemsType> items;
    @XmlElement(required = true)
    protected LodgementType lodgement;
    @XmlElement(name = "shipment_reference", required = true)
    protected String shipmentReference;
    @XmlElement(required = true)
    protected DestinationType destination;

    /**
     * Gets the value of the customerReference1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReference1() {
        return customerReference1;
    }

    /**
     * Sets the value of the customerReference1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReference1(String value) {
        this.customerReference1 = value;
    }

    /**
     * Gets the value of the customerReference2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReference2() {
        return customerReference2;
    }

    /**
     * Sets the value of the customerReference2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReference2(String value) {
        this.customerReference2 = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemsType }
     * 
     * 
     */
    public List<ItemsType> getItems() {
        if (items == null) {
            items = new ArrayList<ItemsType>();
        }
        return this.items;
    }

    /**
     * Gets the value of the lodgement property.
     * 
     * @return
     *     possible object is
     *     {@link LodgementType }
     *     
     */
    public LodgementType getLodgement() {
        return lodgement;
    }

    /**
     * Sets the value of the lodgement property.
     * 
     * @param value
     *     allowed object is
     *     {@link LodgementType }
     *     
     */
    public void setLodgement(LodgementType value) {
        this.lodgement = value;
    }

    /**
     * Gets the value of the shipmentReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentReference() {
        return shipmentReference;
    }

    /**
     * Sets the value of the shipmentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentReference(String value) {
        this.shipmentReference = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link DestinationType }
     *     
     */
    public DestinationType getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationType }
     *     
     */
    public void setDestination(DestinationType value) {
        this.destination = value;
    }

}
