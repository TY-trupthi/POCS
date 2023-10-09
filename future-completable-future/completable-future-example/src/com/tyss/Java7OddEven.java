package com.tyss;

public class Java7OddEven implements Runnable {

	Object obj;

	public Java7OddEven(Object obj) {
		super();
		this.obj = obj;
	}

	static int count = 1;

	@Override
	public void run() {
		while (count <= 10) {
			
			//System.out.println("While thread name " + Thread.currentThread().getName() + " count " + count);
			if (count % 2 == 0 && Thread.currentThread().getName().equalsIgnoreCase("Even")) {
				//synchronized (obj) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					System.err.println("Name " + Thread.currentThread().getName() + " Value" + count);
					count++;
//					try {
//						obj.wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				//}
			} else if (count % 2 != 0 && Thread.currentThread().getName().equalsIgnoreCase("Odd")) {
				//synchronized (obj) {
					System.err.println("Name " + Thread.currentThread().getName() + " Value" + count);
					count++;
					//obj.notify();
				//}
			}

		}

	}

	public static void main(String[] args) {
		Object object = new Object();
		Runnable r1 = new Java7OddEven(object);
		Runnable r2 = new Java7OddEven(object);
		Thread t1 = new Thread(r1, "Even");
		t1.start();
		Thread t2 = new Thread(r2, "Odd");
		t2.start();
	}

}
