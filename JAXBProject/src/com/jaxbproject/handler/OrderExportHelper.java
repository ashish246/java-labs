package com.jaxbproject.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.jaxbproject.export.AddressDetails;
import com.jaxbproject.export.Article;
import com.jaxbproject.export.Consignment;
import com.jaxbproject.export.ConsignmentEnvelope;
import com.jaxbproject.export.Customer;
import com.jaxbproject.export.DeliveryContacts;
import com.jaxbproject.export.Individual;
import com.jaxbproject.export.Manifest;
import com.jaxbproject.export.ManifestEnvelope;
import com.jaxbproject.export.MeasurementData;
import com.jaxbproject.export.Name;

public class OrderExportHelper {
	public static void main(String[] args) throws JAXBException {
		createExportXML();
	}

	/**
	 * populate the order details in the POJOs and convert it to XML
	 * 
	 * @param pOrderId
	 * @param pExportTemplateName
	 * @return manifestXML
	 */
	public static void createExportXML() {

		ManifestEnvelope tManifestEnvelope = null;
		try {
			tManifestEnvelope = createResponseEnvelope();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		if (tManifestEnvelope == null) {
			return;
		}

		// parsing the order details into the XML
		String tXML = JAXBHandler.parseObjectToXML(tManifestEnvelope,
				ManifestEnvelope.class);

		//System.out.println(tXML);
		/*
		 * else{ String manifestBodyXML =
		 * JAXBHandler.parseObjectToXML(manifestEnvelope,
		 * ManifestEnvelope.class); String manifestHeaderXML =
		 * JAXBHandler.parseObjectToXML(new InterfaceHeader(),
		 * InterfaceHeader.class); manifestXML =
		 * SOAPHandler.createSOAPMessage(manifestBodyXML, manifestHeaderXML); }
		 */
	}

	/**
	 * Get order details and generate the order export XML and wraps up it in
	 * ManifectEnvelope.
	 * 
	 * @param pOrderId
	 * @return manifestEnvelope
	 */
	private static ManifestEnvelope createResponseEnvelope() {
		// populate the XML data from OrderBO/PO
		return populateManifestResponseXML();
	}

	/**
	 * Populates all the required Order details into the POJOs to build a XML
	 * response.
	 * 
	 * @param tLineItemCtnr
	 * @return ManifestEnvelope
	 */
	public static ManifestEnvelope populateManifestResponseXML() {

		// Get the Merchant Location ID
		String tMerchantLocationID = "MLIDS";

		// get the account number
		String tAccountNumber = "000123456789";

		// set business reference ID. It is
		// ${manifest.number}_${manifest.mlId}_${header.user}.xml
		String tBusinessRefID = "AP0000000022".concat("_")
				.concat(tMerchantLocationID).concat("_").concat("Head_COM")
				.concat(tAccountNumber).concat(".xml");

		Manifest tManifest = new Manifest("action", "AP0000000022", new Date(),
				new Date(), tMerchantLocationID, new Date());

		// populate consignments
		List<ConsignmentEnvelope> tConsigmentEnvelopeList = getConsignmentEnveloopeList(tMerchantLocationID);

		return new ManifestEnvelope(tManifest, tConsigmentEnvelopeList,
				tBusinessRefID);
	}

	/**
	 * Create a list of all different consignment envelope within one manifest.
	 * 
	 * @param pLineItemCtnr
	 * @param pMLID
	 * @return List<ConsignmentEnvelope>
	 */
	private static List<ConsignmentEnvelope> getConsignmentEnveloopeList(
			String pMLID) {

		List<ConsignmentEnvelope> tConsigmentEnvelopeList = new ArrayList<>(0);

		// get the payment account number for the order
		String tAccountNumber = "000987654321";

		// get the consignment for this entry
		Consignment tConsignment = getConsignment("MLID00000011", pMLID,
				"T28S", tAccountNumber);

		// populate Article List of this consigment
		List<Article> tArticleList = new ArrayList<>(0);
		tArticleList = populateArticleList(tConsignment);

		// add this consignment envelope to the list
		tConsigmentEnvelopeList.add(new ConsignmentEnvelope(tConsignment,
				tArticleList));

		return tConsigmentEnvelopeList;
	}

	/**
	 * Create Article list from LineItem list
	 * 
	 * @param pItemList
	 * @param pConsignment
	 * @return List<Article>
	 */
	private static List<Article> populateArticleList(Consignment pConsignment) {

		List<Article> tArticleList = new ArrayList<>(0);

		Article tArticle = getArticle();

		if (tArticle != null) {
			tArticleList.add(tArticle);
		}
		pConsignment.setDangerousGoodsIndicator(true);
		pConsignment.setSignatureRequired(false);
		pConsignment.setPartialDeliveryAllowed(false);

		return tArticleList;
	}

	/**
	 * Get Consignment from LineItemGroup
	 * 
	 * @param pLineItemGroup
	 * @param pConsignmentNo
	 * @param pMLID
	 * @param pServiceType
	 * @param pPostChargeToAccount
	 * @return Consignment
	 */
	private static Consignment getConsignment(String pConsignmentNo,
			String pMLID, String pServiceType, String pPostChargeToAccount) {
		// store delivery & sender contract details
		List<DeliveryContacts> tDeliveryContractList = getDeliveryContactList();

		// set customer reference text
		List<String> tReferenceData = new ArrayList<>(0);
		tReferenceData.add("Customer reference 1");
		tReferenceData.add("Customer reference 2");

		Boolean tTrackingEmailEnabled = false;

		return new Consignment("action", pConsignmentNo, new Date(), pMLID,
				pServiceType, tDeliveryContractList, true, false, new Date(),
				new Date(), tReferenceData, false, true,
				tTrackingEmailEnabled == null ? false : tTrackingEmailEnabled,
				pPostChargeToAccount);
	}

	/**
	 * Get Article from LineItem
	 * 
	 * @param tLineItem
	 * @return Article
	 */
	private static Article getArticle() {

		MeasurementData tMeasurementData = getMeasurementData();

		return new Article("action", "MLID000000011232546464",
				tMeasurementData, "Jaxb Testing",
				"MLID000000011232546464skjhdsjhdsjh9r9", new Date());
	}

	/**
	 * Get measurement details of the Article
	 * 
	 * @param tLineItem
	 * @return MeasurementData
	 */
	public static MeasurementData getMeasurementData() {

		return new MeasurementData(5.0, 5.0, 5.0, 5.0);
	}

	/**
	 * Generate delivery contact list based the LineItemGroup AVs
	 * 
	 * @param pLineItemGroup
	 * @return List of DeliveryContacts
	 */
	private static List<DeliveryContacts> getDeliveryContactList() {

		List<DeliveryContacts> contactsList = new ArrayList<>(0);

		// store Delivery Contracts
		DeliveryContacts deliveryContract = getContractDetail("Delivery",
				"DESTINATION_ADDRESS");
		contactsList.add(deliveryContract);

		// store sender contract
		DeliveryContacts senderContract = getContractDetail("Sender",
				"LODGEMENT_ADDRESS");
		contactsList.add(senderContract);

		return contactsList;
	}

	/**
	 * Get the customer/address details from the custom attributes of
	 * LineItemGroup
	 * 
	 * @param ligLPO
	 * @param customerType
	 * @param pAddressType
	 * @return DeliveryContacts
	 */
	private static DeliveryContacts getContractDetail(String customerType,
			String addressType) {

		String tDeliveryInstruction = null;
		tDeliveryInstruction = "skvhsdkvhdsklvhdsklv";

		return new DeliveryContacts(getCustomerDetail(), customerType,
				tDeliveryInstruction);
	}

	/**
	 * Populates the customer/Address details in the Customer object
	 * 
	 * @param address
	 * @return Customer
	 */
	private static Customer getCustomerDetail() {
		return new Customer(getIndividualDetails(), getCustomerAdderss(),
				"atripathi@intershop.com", "469700048", "123465789");
	}

	/**
	 * Get object of Individual with Name details.
	 * 
	 * @param address
	 * @return Individual
	 */
	private static Individual getIndividualDetails() {
		List<Name> tNameList = new ArrayList<>(0);
		// set name
		Name tName = new Name("qweqw", "tripathi");
		tNameList.add(tName);

		return new Individual(tNameList);
	}

	/**
	 * Get AddressDetails for a Customer
	 * 
	 * @param address
	 * @return AddressDetails
	 */
	private static AddressDetails getCustomerAdderss() {
		// store address details
		List<String> tAddressLineList = new ArrayList<>(0);
		tAddressLineList.add("Line1");
		tAddressLineList.add("Line2");

		return new AddressDetails(tAddressLineList, "Melbournse", "Vic",
				"3000", "AU");

	}

}
