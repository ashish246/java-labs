package com.reflection.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class SuperArrayDemo {

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		
		long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
		
		SuperArray array = new SuperArray(SUPER_SIZE);
		
		System.out.println("Array size:" + array.size()); // 4294967294
		
		long sum = 0;
		for (int i = 0; i < 100; i++) {
			array.set((long) Integer.MAX_VALUE + i, (byte) 3);
			sum += array.get((long) Integer.MAX_VALUE + i);
		}
		System.out.println("Sum of 100 elements:" + sum); // 300
	}

}

class SuperArray {
	private final static int BYTE = 1;
	private long size;
	private long address;

	public SuperArray(long size) {
		this.size = size;
		address = getUnsafe().allocateMemory(size * BYTE);
	}

	public void set(long i, byte value) {
		getUnsafe().putByte(address + i * BYTE, value);
	}

	public int get(long idx) {
		return getUnsafe().getByte(address + idx * BYTE);
	}

	public long size() {
		return size;
	}

	public Unsafe getUnsafe() {
		Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal
		// reference
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
	}
}