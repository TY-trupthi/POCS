package com.tyss;

public class VolatileTest {
	
	private static volatile int counterValue;

	public static void main(String[] args) {
		
		System.err.println("Came");

		Thread t1 = new Thread(() -> {
			int localCounter = counterValue;
			while (localCounter<10) {
				if (localCounter!=counterValue) {
					System.err.println("It is different");	
					localCounter = counterValue;
				}
			}
		});

		Thread t2 = new Thread(() -> {
			int localCounter = counterValue;
			
			while (localCounter<10) {
				System.err.println("Incrementing " + localCounter);
				counterValue = ++localCounter;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}

}
