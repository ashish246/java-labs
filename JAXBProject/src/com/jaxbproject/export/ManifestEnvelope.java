package com.jaxbproject.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ManifestEnvelope", namespace = "http://www.auspost.com.au/Schema/CommonMessageModel/ActivityEvent:v4")
@XmlType(propOrder = { "manifest", "consignmentEnvelopeList", "businessRefernceID" })
@XmlAccessorType(XmlAccessType.NONE)
public class ManifestEnvelope
{

    public Manifest mManifest;

    public List<ConsignmentEnvelope> mConsignmentEnvelopeList;

    public String mBusinessRefernceID;

    public ManifestEnvelope()
    {
    }

    public ManifestEnvelope(Manifest pManifest, List<ConsignmentEnvelope> pConsignmentEnvelopeList,
                    String pBusinessRefernceID)
    {
        mManifest = pManifest;
        mConsignmentEnvelopeList = pConsignmentEnvelopeList;
        mBusinessRefernceID = pBusinessRefernceID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mManifest, mConsignmentEnvelopeList, mBusinessRefernceID);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof ManifestEnvelope))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return the mManifest
     */
    @XmlElement(name = "Manifest")
    public Manifest getManifest()
    {
        if (mManifest == null)
        {
            mManifest = new Manifest();
        }
        return mManifest;
    }

    /**
     * @return the mConsignmentEnvelope
     */
    @XmlElement(name = "ConsignmentEnvelope")
    public List<ConsignmentEnvelope> getConsignmentEnvelopeList()
    {
        if (mConsignmentEnvelopeList == null)
        {
            mConsignmentEnvelopeList = new ArrayList<>();
        }
        return mConsignmentEnvelopeList;
    }

    /**
     * @return mBusinessRefernceID
     */
    @XmlElement(name = "BusinessRefernceID")
    public String getBusinessRefernceID()
    {
        return mBusinessRefernceID;
    }

}
