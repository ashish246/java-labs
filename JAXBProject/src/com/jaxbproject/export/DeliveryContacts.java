package com.jaxbproject.export;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "customer", "deliveryContactType", "deliveryInstruction" })
@XmlAccessorType(XmlAccessType.NONE)
public class DeliveryContacts
{

    public Customer mCustomer;

    public String mDeliveryContactType;

    public String mDeliveryInstruction;

    public DeliveryContacts()
    {
    }

    public DeliveryContacts(Customer pCustomer, String pDeliveryContactType, String pDeliveryInstruction)
    {
        mCustomer = pCustomer;
        mDeliveryContactType = pDeliveryContactType;
        mDeliveryInstruction = pDeliveryInstruction;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mCustomer, mDeliveryContactType, mDeliveryInstruction);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof DeliveryContacts))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return the mCustomer
     */
    @XmlElement(name = "Customer", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/InvolvedParty/Customer:v1")
    public Customer getCustomer()
    {
        if (mCustomer == null)
        {
            mCustomer = new Customer();
        }
        return mCustomer;
    }

    /**
     * @return the mDeliveryContactType
     */
    @XmlElement(name = "DeliveryContactType", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Mail_Product:v2")
    public String getDeliveryContactType()
    {
        return mDeliveryContactType;
    }

    /**
     * @return mDeliveryInstruction
     */
    @XmlElement(name = "Comments", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Mail_Product:v2")
    public String getDeliveryInstruction()
    {
        return mDeliveryInstruction;
    }

}
