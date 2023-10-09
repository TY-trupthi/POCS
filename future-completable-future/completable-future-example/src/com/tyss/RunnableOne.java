package com.tyss;

import java.util.Iterator;

public class RunnableOne implements Runnable{
	
	Object obj;

	public RunnableOne(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i=i+2) {
			synchronized (obj) {
				System.err.println("Name " + Thread.currentThread().getName() + " Value " + i);
				try {
					obj.notify();
					obj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
