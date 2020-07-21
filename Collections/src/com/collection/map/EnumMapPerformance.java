package com.collection.map;

import java.util.EnumMap;
import java.util.HashMap;

public class EnumMapPerformance {

	enum Importance {
		Low, Medium, High, Critical
	}

	public static void main(String[] args) throws Exception {

		EnumMap<Importance, String> e = new EnumMap<>(Importance.class);
		e.put(Importance.Low, "=Low");
		e.put(Importance.High, "=High");

		HashMap<Importance, String> h = new HashMap<>();
		h.put(Importance.Low, "=Low");
		h.put(Importance.High, "=High");

		long t1 = System.currentTimeMillis();

		// ... Check EnumMap.
		for (int i = 0; i < 10000000; i++) {
			if (!e.containsKey(Importance.Low)) {
				throw new Exception();
			}
		}

		long t2 = System.currentTimeMillis();

		// ... Check HashMap.
		for (int i = 0; i < 10000000; i++) {
			if (!h.containsKey(Importance.Low)) {
				throw new Exception();
			}
		}

		long t3 = System.currentTimeMillis();

		// ... Times.
		System.out.println(t2 - t1);
		System.out.println(t3 - t2);
	}
}
