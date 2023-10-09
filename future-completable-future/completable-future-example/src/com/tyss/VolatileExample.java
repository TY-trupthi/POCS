package com.tyss;

public class VolatileExample {
	
	private volatile Boolean falg = Boolean.FALSE;

	public Boolean getFalg() {
		return falg;
	}

	public void toggleFlag() {
		 for (int i = 0; i < 1000000; i++) {
	            // Some computational work to simulate a longer operation
	        }
		falg = !falg;
    }
	
}
