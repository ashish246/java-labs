package com.jaxbproject.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Location:v1", propOrder = { "addressLineList",
                "suburbOrPlaceOrLocality", "stateOrTerritory", "postCode", "countryCode" })
@XmlAccessorType(XmlAccessType.NONE)
public class AddressDetails
{

    public List<String> mAddressLineList;

    public String mSuburbOrPlaceOrLocality;

    public String mStateOrTerritory;

    public String mPostCode;

    public String mCountryCode;

    public AddressDetails()
    {
    }

    public AddressDetails(List<String> pAddressLineList, String pSuburbOrPlaceOrLocality, String pStateOrTerritory,
                    String pPostCode, String pCountryCode)
    {
        mAddressLineList = pAddressLineList;
        mSuburbOrPlaceOrLocality = pSuburbOrPlaceOrLocality;
        mStateOrTerritory = pStateOrTerritory;
        mPostCode = pPostCode;
        mCountryCode = pCountryCode;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mAddressLineList, mSuburbOrPlaceOrLocality, mStateOrTerritory, mPostCode, mCountryCode);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof AddressDetails))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return the mAddressLineList
     */
    @XmlElementWrapper(name = "UnstructuredAddress")
    @XmlElement(name = "AddressLine")
    public List<String> getAddressLineList()
    {
        if (mAddressLineList == null)
        {
            mAddressLineList = new ArrayList<>();
        }
        return mAddressLineList;
    }

    /**
     * @return the mSuburbOrPlaceOrLocality
     */
    @XmlElement(name = "SuburbOrPlaceOrLocality")
    public String getSuburbOrPlaceOrLocality()
    {
        return mSuburbOrPlaceOrLocality;
    }

    /**
     * @return the mStateOrTerritory
     */
    @XmlElement(name = "StateOrTerritory")
    public String getStateOrTerritory()
    {
        return mStateOrTerritory;
    }

    /**
     * @return the mPostCode
     */
    @XmlElement(name = "PostCode")
    public String getPostCode()
    {
        return mPostCode;
    }

    /**
     * @return the mCountryCodeList
     */
    // @XmlElementWrapper(name = "Country")
    @XmlElement(name = "CountryCode")
    public String getCountryCode()
    {
        return mCountryCode;
    }

}
