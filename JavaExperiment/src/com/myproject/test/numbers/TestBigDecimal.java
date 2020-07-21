package com.myproject.test.numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigDecimal {

	public static void main(String[] args) {
		
		BigDecimal tBigDec = new BigDecimal(111168.78);
		BigDecimal finalBig = tBigDec.setScale(-1, RoundingMode.HALF_UP);
		
		/*BigDecimal tActualQuantity = new BigDecimal("abcd")
        .multiply(new BigDecimal(1000));*/
		
		//System.out.println("tBigDec -------->"+finalBig);
		
		BigDecimal tBigDec1 = new BigDecimal(0);
		BigDecimal tBigDec2 = new BigDecimal(0.0000);
		BigDecimal tBigDec3 = new BigDecimal("0");
		BigDecimal tBigDec4 = new BigDecimal("0.0000000");
		
		System.out.println("tBigDec1 -> " + tBigDec1.compareTo(BigDecimal.ZERO));
		System.out.println("tBigDec2 -> " + tBigDec2.compareTo(BigDecimal.ZERO));
		System.out.println("tBigDec3 -> " + tBigDec3.compareTo(BigDecimal.ZERO));
		System.out.println("tBigDec4 -> " + tBigDec4.compareTo(BigDecimal.ZERO));
		
		System.out.println("Double value -> " + tBigDec1.doubleValue());
	}
}
