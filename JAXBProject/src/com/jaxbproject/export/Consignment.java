package com.jaxbproject.export;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Consignment")
@XmlType(namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Mail_Product:v2", propOrder = {
		"actionCode", "consignmentId", "consignmentDateTime",
		"merchantLocationId", "transportServiceType", "deliveryContactsList",
		"referenceDetailsList", "signatureRequired", "dangerousGoodsIndicator",
		"internationalDelivery", "expectedDespatchDate",
		"recordModificationDateTime", "partialDeliveryAllowed",
		"trackingEmailEnabled", "postChargeToAccount" })
@XmlAccessorType(XmlAccessType.NONE)
public class Consignment {
	public String mActionCode;

	public String mConsignmentId;

	public Date mConsignmentDateTime;

	public String mMerchantLocationId;

	public String mTransportServiceType;

	public List<DeliveryContacts> mDeliveryContactsList;

	public boolean mSignatureRequired;

	public boolean mInternationalDelivery;

	public Date mExpectedDespatchDate;

	public Date mRecordModificationDateTime;

	public List<String> mReferenceDetailsList;

	public boolean mDangerousGoodsIndicator;

	public boolean mPartialDeliveryAllowed;

	public boolean mTrackingEmailEnabled;

	public String mPostChargeToAccount;

	public Consignment() {
	}

	public Consignment(String pActionCode, String pConsignmentId,
			Date pConsignmentDateTime, String pMerchantLocationId,
			String pTransportServiceType,
			List<DeliveryContacts> pDeliveryContactsList,
			boolean pSignatureRequired, boolean pInternationalDelivery,
			Date pExpectedDespatchDate, Date pRecordModificationDateTime,
			List<String> pReferenceDetailsList,
			boolean pDangerousGoodsIndicator, boolean pPartialDeliveryAllowed,
			boolean pTrackingEmailEnabled, String pPostChargeToAccount) {
		mActionCode = pActionCode;
		mConsignmentId = pConsignmentId;
		mConsignmentDateTime = pConsignmentDateTime;
		mMerchantLocationId = pMerchantLocationId;
		mTransportServiceType = pTransportServiceType;
		mDeliveryContactsList = pDeliveryContactsList;
		mSignatureRequired = pSignatureRequired;
		mInternationalDelivery = pInternationalDelivery;
		mExpectedDespatchDate = pExpectedDespatchDate;
		mRecordModificationDateTime = pRecordModificationDateTime;
		mReferenceDetailsList = pReferenceDetailsList;
		mDangerousGoodsIndicator = pDangerousGoodsIndicator;
		mPartialDeliveryAllowed = pPartialDeliveryAllowed;
		mTrackingEmailEnabled = pTrackingEmailEnabled;
		mPostChargeToAccount = pPostChargeToAccount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mActionCode, mConsignmentId, mConsignmentDateTime,
				mMerchantLocationId, mTransportServiceType,
				mDeliveryContactsList, mSignatureRequired,
				mInternationalDelivery, mExpectedDespatchDate,
				mRecordModificationDateTime, mReferenceDetailsList,
				mDangerousGoodsIndicator, mPartialDeliveryAllowed,
				mPostChargeToAccount);
	}

	@Override
	public boolean equals(Object pObject) {
		if (!(pObject instanceof Consignment)) {
			return false;
		}

		return Objects.equals(this, pObject);
	}

	/**
	 * @return the mActionCode
	 */
	@XmlElement(name = "ActionCode")
	public String getActionCode() {
		return mActionCode;
	}

	/**
	 * @return the mConsignmentId
	 */
	@XmlElement(name = "ConsignmentId")
	public String getConsignmentId() {
		return mConsignmentId;
	}

	/**
	 * @return the mConsignmentDateTime
	 */
	@XmlElement(name = "ConsignmentDateTime")
	public Date getConsignmentDateTime() {
		return mConsignmentDateTime;
	}

	/**
	 * @return the mMerchantLocationId
	 */
	@XmlElement(name = "MerchantLocationId")
	public String getMerchantLocationId() {
		return mMerchantLocationId;
	}

	/**
	 * @return the mTransportServiceType
	 */
	@XmlElement(name = "TransportServiceType")
	public String getTransportServiceType() {
		return mTransportServiceType;
	}

	/**
	 * @return the mDeliveryContactsList
	 */
	@XmlElement(name = "DeliveryContacts")
	public List<DeliveryContacts> getDeliveryContactsList() {
		if (mDeliveryContactsList == null) {
			mDeliveryContactsList = new ArrayList<>();
		}
		return mDeliveryContactsList;
	}

	/**
	 * @return the mSignatureRequired
	 */
	@XmlElement(name = "SignatureRequired")
	public boolean isSignatureRequired() {
		return mSignatureRequired;
	}

	/**
	 * @return the mInternationalDelivery
	 */
	@XmlElement(name = "InternationalDelivery")
	public boolean isInternationalDelivery() {
		return mInternationalDelivery;
	}

	/**
	 * @return the mExpectedDespatchDate
	 */
	@XmlElement(name = "ExpectedDespatchDate")
	public Date getExpectedDespatchDate() {
		return mExpectedDespatchDate;
	}

	/**
	 * @return the mRecordModificationDateTime
	 */
	@XmlElement(name = "RecordModificationDateTime")
	public Date getRecordModificationDateTime() {
		return mRecordModificationDateTime;
	}

	/**
	 * @return mReferenceDetailsList
	 */
	@XmlElement(name = "ReferenceDetails")
	public List<String> getReferenceDetailsList() {
		if (mReferenceDetailsList == null) {
			mReferenceDetailsList = new ArrayList<>();
		}
		return mReferenceDetailsList;
	}

	/**
	 * @return true if it is dangerous goods
	 */
	@XmlElement(name = "DangerousGoodsIndicator")
	public boolean isDangerousGoodsIndicator() {
		return mDangerousGoodsIndicator;
	}

	/**
	 * @param pDangerousGoodsIndicator
	 */
	public void setDangerousGoodsIndicator(boolean pDangerousGoodsIndicator) {
		this.mDangerousGoodsIndicator = pDangerousGoodsIndicator;
	}

	/**
	 * @param pSignatureRequired
	 */
	public void setSignatureRequired(boolean pSignatureRequired) {
		this.mSignatureRequired = pSignatureRequired;
	}

	@XmlElement(name = "PartialDeliveryAllowed")
	public boolean isPartialDeliveryAllowed() {
		return mPartialDeliveryAllowed;
	}

	public void setPartialDeliveryAllowed(boolean pPartialDeliveryAllowed) {
		this.mPartialDeliveryAllowed = pPartialDeliveryAllowed;
	}

	@XmlElement(name = "TrackingEmailEnabled")
	public boolean isTrackingEmailEnabled() {
		return mTrackingEmailEnabled;
	}

	public void setTrackingEmailEnabled(boolean pTrackingEmailEnabled) {
		mTrackingEmailEnabled = pTrackingEmailEnabled;
	}

	@XmlElement(name = "PostChargeToAccount")
	public String getPostChargeToAccount() {
		return mPostChargeToAccount;
	}

}
