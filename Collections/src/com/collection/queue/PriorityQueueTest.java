package com.collection.queue;

import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {

		PriorityQueue<String> adjectives = new PriorityQueue<String>();
		adjectives.add("Amazing"); // this line
		adjectives.add("Fantastic");
		// adjectives.add("ZAmazing"); //change to this and output changes
		adjectives.add("Outstanding");
		System.out.println(adjectives.poll() + " : " + adjectives.peek());
	}
}
