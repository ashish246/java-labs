package com.myproject.test.uuid;

import java.util.UUID;

public class TestUUID {

	public static void main(String[] args) {

		System.out.println("Random UUID_1: "+ UUID.randomUUID());
		System.out.println("Random UUID_2: "+ UUID.randomUUID().toString());
		
		System.out.println("Random UUID_3: "+ new UUID(1L, 2L));
		System.out.println("Random UUID_4: "+ new UUID(1L, 2L).fromString("5ca57068-5007-4056-b216-442022ed3445"));
		System.out.println("Random UUID_5: "+ new UUID(1L, 2L).nameUUIDFromBytes("Tripathi".getBytes()));
		
		
		
	}

}
