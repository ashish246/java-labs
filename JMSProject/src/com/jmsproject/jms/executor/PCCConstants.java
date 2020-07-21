package com.jmsproject.jms.executor;

public class PCCConstants
{

    // defined for the order export
    public static final String ORDER_NUMBER_PATTERN = "com.AusPostCOM-PCC.orderNumberPattern";
    public static final String ORDER_NUMBER_PATTERN_DEFAULT = "AP0000000000";
    public static final String CUSTOMER_TYPE_DELIVERY = "Delivery";
    public static final String CUSTOMER_TYPE_SENDER = "Sender";
    public static final String CUSTOMER_NAME_USAGE = "IND";
    public static final String CUSTOMER_BUSINESS_NAME_USAGE = "ORG";
    public static final String COUNTRY_CODE_AU = "AU";
    public static final String ACTION_CODE = "N";
    public static final String HEADER_COM = "COM";
    public static final boolean INTERNATIONAL_DELIVERY = false;
    // It is of length 3-4 alphabetical chars like RPM etc.
    public static final String MERCHANT_LOCATION_ID = "merchantLocationId";
    // It is a numeric string identifier passed in the request for that user.
    public static final String CONSIGNMENT_NO_SERIES_SEQ = "0000000";
    public static final String CONSIGNMENT_NO_SERIES_IDENTIFIER = "Seller.ConsignmentNo.";

    // For order export XML generation
    public static final String ORDER_EXPORT_XML_TEMPLATE = "order/OrderExportXMLTemplate";
    public static final String PCMS_MANIFEST_EXPORT_DICT_NAME = "ManifestEnvelope";

    public static final String POSTAGE_PAID_INDICATOR = "0";

    // regex to validate alphabets within string
    public static final String STRING_ALPHABETS_REGEX = "[a-zA-Z]+";

    public static final int PAGEABLE_AMOUNT = 10;
    public static final int PAGEABLE_OFFSET = 0;

    public static final String SHIPMENT_SORT_CREATION_DATE = "CreationDate";

    public static final String PCC_PURCHASE_CURR = "AUD";

    public static final String USER_WORK_CENTRE = "workCentreName";
    
    public static final String BASKET_MAX_ITEM_SIZE = "BasketMaxItemSize";
    public static final Integer BASKET_MAX_ITEM_SIZE_DEFAULT = 9999;
    
    // Application settings
    public static final String MAX_PARALLEL_THREADS_PER_REQUEST = "com.AusPostCOM-PCC.maxParallelThreadsPerRequest";
    public static final int MAX_PARALLEL_THREADS_PER_REQUEST_DEFAULT = 10;
    
    public static final String MAX_EXECUTOR_SERVICE_TIMEOUT = "com.AusPostCOM-PCC.maxExecutorServiceTimeout";
    public static final int MAX_EXECUTOR_SERVICE_TIMEOUT_DEFAULT = 1800;
    
    public static final String MAX_EXECUTOR_SERVICE_PLIMPORT_TIMEOUT = "com.AusPostCOM-PCC.maxExecutorServiceTimeoutForPLImport";
    public static final int MAX_EXECUTOR_SERVICE_PLIMPORT_TIMEOUT_DEFAULT = 21600;
    

}
