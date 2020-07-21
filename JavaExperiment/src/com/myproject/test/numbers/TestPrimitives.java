package com.myproject.test.numbers;

public class TestPrimitives
{

	public static Double mHeightCM;

	public static Double mWidthCM;

	public static Double mLengthCM;

	public static void main(String[] args)
	{

		Double tHeight = 2.49;
		Double tlength = 2.51;
		Double tWidth = 2.5;
		
		MeasurementData(tHeight, tlength, tWidth);

		//System.out.println("calculate -----> " + tlength * tWidth * tHeight);

		System.out.println("tHeight -----> " + mHeightCM);
		System.out.println("tlength -----> " + mLengthCM);
		System.out.println("tWidth -----> " + mWidthCM);

		Long tLen = Math.round(tlength);
		//System.out.println("tLen -----> " + tLen.doubleValue());
		
		boolean t1 = true;
		boolean t2 = false;
		
		System.out.println("Status: "+ Boolean.compare(t1, t2));

	}

	public static void MeasurementData(Double pHeightCM, Double pLengthCM, Double pWidthCM)
	{
		// Rounding done for Production Defect | DE3338

		if (pHeightCM != null)
		{
			Long tHeight = Math.round(pHeightCM);
			mHeightCM = tHeight.doubleValue();
		}
		if (pLengthCM != null)
		{
			Long tLength = Math.round(pLengthCM);
			mLengthCM = tLength.doubleValue();
		}
		if (pWidthCM != null)
		{
			Long tWidth = Math.round(pWidthCM);
			mWidthCM = tWidth.doubleValue();
		}

	}

}
