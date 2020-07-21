package com.keyword.enums;

public class EnumDemo
{

	public static void main(String[] args)
	{

		String tSOD = "SIGNATURE_ON_DELIVERY";
		
		System.out.println(tSOD.equals(EProductFeature.SIGNATURE_ON_DELIVERY));
		
		System.out.println("SIGNATURE_ON_DELIVERY".equals(EProductFeature.SIGNATURE_ON_DELIVERY));
		
		//System.out.println((tSOD == EProductFeature.SIGNATURE_ON_DELIVERY));
		
		System.out.println((EProductFeature.valueOf(tSOD) == EProductFeature.SIGNATURE_ON_DELIVERY));
		
		System.out.println(tSOD.equals(EProduct.SIGNATURE_ON_DELIVERY));
		
		System.out.println("SIGNATURE_ON_DELIVERY".equals(EProduct.SIGNATURE_ON_DELIVERY));
		
		//System.out.println("SIGNATURE_ON_DELIVERY" == EProduct.SIGNATURE_ON_DELIVERY);
	}

}


enum EProductFeature
{
    TRANSIT_COVER(String.class),

    SIGNATURE_ON_DELIVERY(Boolean.class);

    private Class<?> mMapperClass;

    private EProductFeature(Class<?> pMapperClass)
    {
        mMapperClass = pMapperClass;
    }
}

enum EProduct
{
    TRANSIT_COVER,

    SIGNATURE_ON_DELIVERY;
}