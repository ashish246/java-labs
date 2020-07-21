package com.keyword.enums;

/**
 * Enum constants can override generalized method defined in the enum body.
 * 
 * Enum can have abstract method declared in it’s body provided each enum constants must implement it.
 * 
 * @author Administrator
 * 
 */
public class EnumExample1 {
	public static void main(String[] args) {
		enums.FIRST.commonMethod(); // Output : Common method Overridden in
									// FIRST

		enums.SECOND.commonMethod(); // Output : Common method Overridden in
										// SECOND

		enums.THIRD.commonMethod(); // Output : Common method Overridden in
									// THIRD
	}
}

enum enums {
	FIRST {
		@Override
		void commonMethod() {
			System.out.println("Common method Overridden in FIRST");
		}
	},

	SECOND {
		@Override
		void commonMethod() {
			System.out.println("Common method Overridden in SECOND");
		}
	},

	THIRD {
		@Override
		void commonMethod() {
			System.out.println("Common method Overridden in THIRD");
		}
	};

	void commonMethod() {
		System.out.println("Generalized method, Common to all constants");
	}
}
