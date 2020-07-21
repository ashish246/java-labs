package com.jaxbproject.export;

import java.util.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Manifest")
@XmlType(namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Mail_Product:v2", propOrder = { "actionCode",
                "manifestNumber", "arrivedDate", "collectionDate", "merchantLocationId", "recordModificationDateTime" })
@XmlAccessorType(XmlAccessType.NONE)
public class Manifest
{

    public String mActionCode;

    public String mManifestNumber;

    public Date mArrivedDate;

    public Date mCollectionDate;

    public String mMerchantLocationId;

    public Date mRecordModificationDateTime;

    public Manifest()
    {
    }

    public Manifest(String pActionCode, String pManifestNumber, Date pArrivedDate, Date pCollectionDate,
                    String pMerchantLocationId, Date pRecordModificationDateTime)
    {
        mActionCode = pActionCode;
        mManifestNumber = pManifestNumber;
        mArrivedDate = pArrivedDate;
        mCollectionDate = pCollectionDate;
        mMerchantLocationId = pMerchantLocationId;
        mRecordModificationDateTime = pRecordModificationDateTime;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mActionCode, mManifestNumber, mArrivedDate, mCollectionDate, mMerchantLocationId,
                        mRecordModificationDateTime);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof Manifest))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return
     */
    @XmlElement(name = "ActionCode")
    public String getActionCode()
    {
        return mActionCode;
    }

    /**
     * @return
     */
    @XmlElement(name = "ManifestNumber")
    public String getManifestNumber()
    {
        return mManifestNumber;
    }

    /**
     * @return
     */
    @XmlElement(name = "ArrivedDate")
    public Date getArrivedDate()
    {
        return mArrivedDate;
    }

    /**
     * @return
     */
    @XmlElement(name = "CollectionDate")
    public Date getCollectionDate()
    {
        return mCollectionDate;
    }

    /**
     * @return
     */
    @XmlElement(name = "MerchantLocationId")
    public String getMerchantLocationId()
    {
        return mMerchantLocationId;
    }

    /**
     * @return
     */
    @XmlElement(name = "RecordModificationDateTime")
    public Date getRecordModificationDateTime()
    {
        return mRecordModificationDateTime;
    }

}
