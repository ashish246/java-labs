package com.jaxbproject.export;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "individual", "addressDetails", "email", "mobile",
		"customerID" })
@XmlAccessorType(XmlAccessType.NONE)
public class Customer {
	public Individual mIndividual;

	public AddressDetails mAddressDetails;

	private String mEmail;

	private String mMobile;

	private String mCustomerID;

	public Customer() {
	}

	public Customer(Individual pIndividual, AddressDetails pAddressDetails,
			String pEmail, String pMobile, String pApcn) {
		mIndividual = pIndividual;
		mAddressDetails = pAddressDetails;
		mEmail = pEmail;
		mMobile = pMobile;
		mCustomerID = pApcn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mIndividual, mAddressDetails, mEmail, mMobile,
				mCustomerID);
	}

	@Override
	public boolean equals(Object pObject) {
		if (!(pObject instanceof Customer)) {
			return false;
		}

		return Objects.equals(this, pObject);
	}

	/**
	 * @return the mIndividual
	 */
	@XmlElement(name = "Individual", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/InvolvedParty:v1")
	public Individual getIndividual() {
		if (mIndividual == null) {
			mIndividual = new Individual();
		}
		return mIndividual;
	}

	/**
	 * @return the mAddressDetailsList
	 */
	// @XmlElementWrapper(name = "Address", namespace =
	// "http://www.auspost.com.au/Schema/CommonDataModel/InvolvedParty:v1")
	@XmlElement(name = "AddressDetails", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Location:v1")
	public AddressDetails getAddressDetails() {
		if (mAddressDetails == null) {
			mAddressDetails = new AddressDetails();
		}
		return mAddressDetails;
	}

	@XmlElement(name = "Email", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Location:v1")
	public String getEmail() {
		return mEmail;
	}

	@XmlElement(name = "Mobile", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Location:v1")
	public String getMobile() {
		return mMobile;
	}

	@XmlElement(name = "CustomerID", namespace = "http://www.auspost.com.au/Schema/CommonDataModel/Location:v1")
	public String getCustomerID() {
		return mCustomerID;
	}

}
