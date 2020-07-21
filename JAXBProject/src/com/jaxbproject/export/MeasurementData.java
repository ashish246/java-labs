package com.jaxbproject.export;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Common:v1", propOrder = { "heightCM", "widthCM",
                "lengthCM", "weightKG" })
@XmlAccessorType(XmlAccessType.NONE)
public class MeasurementData
{

    public Double mHeightCM;

    public Double mWidthCM;

    public Double mLengthCM;

    public Double mWeightKG;

    public MeasurementData()
    {
    }

    public MeasurementData(Double pHeightCM, Double pWidthCM, Double pLengthCM, Double pWeightKG)
    {
        mHeightCM = pHeightCM;
        mWidthCM = pWidthCM;
        mLengthCM = pLengthCM;
        mWeightKG = pWeightKG;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mHeightCM, mWidthCM, mLengthCM, mWeightKG);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof MeasurementData))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return
     */
    @XmlElement(name = "HeightCM")
    public Double getHeightCM()
    {
        return mHeightCM;
    }

    /**
     * @return
     */
    @XmlElement(name = "WidthCM")
    public Double getWidthCM()
    {
        return mWidthCM;
    }

    /**
     * @return
     */
    @XmlElement(name = "LengthCM")
    public Double getLengthCM()
    {
        return mLengthCM;
    }

    /**
     * @return
     */
    @XmlElement(name = "WeightKG")
    public Double getWeightKG()
    {
        return mWeightKG;
    }

}
