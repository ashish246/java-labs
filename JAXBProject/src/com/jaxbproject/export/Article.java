package com.jaxbproject.export;

import java.util.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Article")
@XmlType(namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Mail_Product:v2", propOrder = { "actionCode",
                "articleId", "measurementData", "description", "fullBarcode", "recordModificationDateTime" })
@XmlAccessorType(XmlAccessType.NONE)
public class Article
{

    public String mActionCode;

    public String mArticleId;

    public MeasurementData mMeasurementData;

    public String mDescription;

    public String mFullBarcode;

    public Date mRecordModificationDateTime;

    public Article()
    {
    }

    public Article(String pActionCode, String pArticleId, MeasurementData pMeasurementData, String pDescription,
                    String pFullBarcode, Date pRecordModificationDateTime)
    {
        mActionCode = pActionCode;
        mArticleId = pArticleId;
        mMeasurementData = pMeasurementData;
        mDescription = pDescription;
        mFullBarcode = pFullBarcode;
        mRecordModificationDateTime = pRecordModificationDateTime;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mActionCode, mArticleId, mMeasurementData, mDescription, mFullBarcode,
                        mRecordModificationDateTime);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof Article))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return the mActionCode
     */
    @XmlElement(name = "ActionCode")
    public String getActionCode()
    {
        return mActionCode;
    }

    /**
     * @return the mArticleId
     */
    @XmlElement(name = "ArticleId")
    public String getArticleId()
    {
        return mArticleId;
    }

    /**
     * @return the mMeasurementDataList
     */
    @XmlElement(name = "MeasurementData")
    public MeasurementData getMeasurementData()
    {
        if (mMeasurementData == null)
        {
            mMeasurementData = new MeasurementData();
        }
        return mMeasurementData;
    }

    /**
     * @return the mFullBarcode
     */
    @XmlElement(name = "FullBarcode")
    public String getFullBarcode()
    {
        return mFullBarcode;
    }

    /**
     * @return the mRecordModificationDateTime
     */
    @XmlElement(name = "RecordModificationDateTime")
    public Date getRecordModificationDateTime()
    {
        return mRecordModificationDateTime;
    }

    @XmlElement(name = "Description")
    public String getDescription()
    {
        return mDescription;
    }

}
