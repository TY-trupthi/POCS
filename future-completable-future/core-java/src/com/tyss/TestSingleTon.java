package com.tyss;

public class TestSingleTon {
	
	public static void main(String[] args) {
		SingleTonClass singleTonClassObject = SingleTonClass.getSingleTonClassObject();
		try {
			Object clone = singleTonClassObject.clone();
			System.err.println(clone);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
