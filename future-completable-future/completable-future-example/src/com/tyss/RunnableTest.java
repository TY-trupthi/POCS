package com.tyss;

public class RunnableTest {
	public static void main(String[] args) {
		Object obj = new Object();
		Runnable r1 = new RunnableOne(obj);
		Runnable r2 = new RunnableTwo(obj);
		Thread t1 = new Thread(r1, "Even");
		t1.start();
		Thread t2 = new Thread(r2, "Odd");
		t2.start();
	}

}
