package com.tyss;

public class SingleTonClass implements Cloneable{
	
	private static final SingleTonClass SINGLE_OBJECT  = new SingleTonClass();
	
	private SingleTonClass() {
		super();
	}
	
	public static SingleTonClass getSingleTonClassObject() {
		return SINGLE_OBJECT;
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException("Cloning of Singleton not allowed");
    }

}
