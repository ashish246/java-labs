package com.jaxbproject.export;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://www.auspost.com.au/Schema/CommonDataModel/InvolvedParty:v1", propOrder = { "nameUsage",
                "fullName" })
@XmlAccessorType(XmlAccessType.NONE)
public class Name
{

    public String mNameUsage;

    public String mFullName;

    public Name()
    {
    }

    public Name(String pNameUsage, String pFullName)
    {
        mNameUsage = pNameUsage;
        mFullName = pFullName;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mNameUsage, mFullName);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof Name))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return the mNameUsage
     */
    @XmlElement(name = "NameUsage")
    public String getNameUsage()
    {
        return mNameUsage;
    }

    /**
     * @return the mFullName
     */
    @XmlElement(name = "FullName")
    public String getFullName()
    {
        return mFullName;
    }

}
