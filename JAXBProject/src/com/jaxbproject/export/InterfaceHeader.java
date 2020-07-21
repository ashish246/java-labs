package com.jaxbproject.export;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InterfaceHeader", namespace="http://www.auspost.com.au/Schema/CommonDataModel/Common:v1")
@XmlType(propOrder = { "interfaceName",
                "interfaceVersion", "messageType", "businessReferenceId", "sourceSystemId", "sourceInformation",
                "timeStamp" })
@XmlAccessorType(XmlAccessType.NONE)
public class InterfaceHeader
{

    private String mInterfaceName;

    private String mInterfaceVersion;

    private String mMessageType;

    private String mBusinessReferenceId;

    private String mSourceSystemId;

    private String mSourceInformation;

    private Date mTimeStamp;

    @XmlElement(name = "InterfaceName")
    public String getInterfaceName()
    {
        return mInterfaceName;
    }

    @XmlElement(name = "InterfaceVersion")
    public String getInterfaceVersion()
    {
        return mInterfaceVersion;
    }

    @XmlElement(name = "MessageType")
    public String getMessageType()
    {
        return mMessageType;
    }

    @XmlElement(name = "BusinessReferenceID")
    public String getBusinessReferenceId()
    {
        return mBusinessReferenceId;
    }

    @XmlElement(name = "SourceSystemID")
    public String getSourceSystemId()
    {
        return mSourceSystemId;
    }

    @XmlElement(name = "SourceInformation")
    public String getSourceInformation()
    {
        return mSourceInformation;
    }

    @XmlElement(name = "TimeStamp")
    public Date getTimeStamp()
    {
        return mTimeStamp;
    }

    public void setInterfaceName(String pInterfaceName)
    {
        this.mInterfaceName = pInterfaceName;
    }

    public void setInterfaceVersion(String pInterfaceVersion)
    {
        this.mInterfaceVersion = pInterfaceVersion;
    }

    public void setMessageType(String pMessageType)
    {
        this.mMessageType = pMessageType;
    }

    public void setBusinessReferenceId(String BusinessReferenceId)
    {
        this.mBusinessReferenceId = BusinessReferenceId;
    }

    public void setSourceSystemId(String pSourceSystemId)
    {
        this.mSourceSystemId = pSourceSystemId;
    }

    public void setSourceInformation(String pSourceInformation)
    {
        this.mSourceInformation = pSourceInformation;
    }

    public void setTimeStamp(Date pTimeStamp)
    {
        this.mTimeStamp = pTimeStamp;
    }

}
