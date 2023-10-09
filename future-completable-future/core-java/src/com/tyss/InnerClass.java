package com.tyss;

public class InnerClass {

	private int i;

	public int getData() {
		System.err.println(i);
		return i;
	}

	public class SubClass {

		public void checkInnerClass() {
			System.err.println("I value is " + i + " Calling method " + getData());
		}
	}

}
