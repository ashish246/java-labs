package com.jaxbproject.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ConsignmentEnvelope")
@XmlType(propOrder = { "consignment", "articleList" })
@XmlAccessorType(XmlAccessType.NONE)
public class ConsignmentEnvelope
{
    public Consignment mConsignment;

    public List<Article> mArticleList;

    public ConsignmentEnvelope()
    {
    }

    public ConsignmentEnvelope(Consignment pConsignment, List<Article> pArticleList)
    {
        mConsignment = pConsignment;
        mArticleList = pArticleList;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mConsignment, mArticleList);
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (!(pObject instanceof ConsignmentEnvelope))
        {
            return false;
        }

        return Objects.equals(this, pObject);
    }

    /**
     * @return the mConsignmentList
     */
    @XmlElement(name = "Consignment")
    public Consignment getConsignment()
    {
        if (mConsignment == null)
        {
            mConsignment = new Consignment();
        }
        return mConsignment;
    }

    /**
     * @return the mArticleList
     */
    @XmlElementWrapper(name = "ArticleEnvelope")
    @XmlElement(name = "Article")
    public List<Article> getArticleList()
    {
        if (mArticleList == null)
        {
            mArticleList = new ArrayList<>();
        }
        return mArticleList;
    }

}
