package com.collection.set;

import java.util.BitSet;

public class BitSetDemo {

	public static void main(String[] args) {

		// Set two bits in a BitSet.
		BitSet b = new BitSet();
		b.set(10);
		b.set(100);

		// Get values of these bit positions.
		boolean bit1 = b.get(5);
		boolean bit2 = b.get(10);
		boolean bit3 = b.get(100);

		System.out.println(bit1);
		System.out.println(bit2);
		System.out.println(bit3);
	}
	
	public static void testFlip(String[] args) {
		BitSet b = new BitSet();

		// Set this bit.
		b.set(3);
		System.out.println(b.get(3));

		// Flip the bit.
		b.flip(3);
		System.out.println(b.get(3));
	    }
	
	public static void testRange(String[] args) {
		BitSet b = new BitSet();

		// Set bits in this range to true.
		b.set(2, 5);

		// Display first five bits.
		for (int i = 0; i < 5; i++) {
		    System.out.println(b.get(i));
		}
	 }
	
	public static void testClear(String[] args) {

		BitSet b = new BitSet();

		// Set first four bits to true.
		b.set(0, 4);

		// Clear first two bits.
		b.clear(0, 2);

		// Display first four bits.
		System.out.println(b.get(0));
		System.out.println(b.get(1));
		System.out.println(b.get(2));
		System.out.println(b.get(3));
	 }

	public static void testGetRange(String[] args) {

		BitSet b = new BitSet();
		b.set(3);
		b.set(5);

		// Get range of bits from original set.
		BitSet b2 = b.get(3, 6);

		// Display first five bits.
		for (int i = 0; i < 5; i++) {
		    System.out.println(b2.get(i));
		}
	}
}
