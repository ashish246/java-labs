package com.jaxbproject.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://www.auspost.com.au/Schema/CommonDataModel/InvolvedParty:v1", propOrder = { "nameList" })
@XmlAccessorType(XmlAccessType.NONE)
public class Individual
{

    public List<Name> mNameList;

    public Individual()
    {
    }

    public Individual(List<Name> pNameList)
    {
        mNameList = pNameList;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mNameList);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof Individual))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return the mName
     */
    @XmlElement(name = "Name")
    public List<Name> getNameList()
    {
        if (mNameList == null)
        {
            mNameList = new ArrayList<>();
        }
        return mNameList;
    }

}
