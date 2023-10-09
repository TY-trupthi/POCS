package com.tyss;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OddEvenWithCompletableFuture {

	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		for (int i = 0; i <= 10; i++) {
			getValue(i, newFixedThreadPool);
		}
	}

	public static void getValue(int i, ExecutorService newFixedThreadPool) {
		
		CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
			if (i % 2 != 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("Thread " + Thread.currentThread().getName() + " value " + i);
			}
			return i;
		}, newFixedThreadPool);
		supplyAsync.join();
		CompletableFuture<Integer> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
			
			if (i % 2 == 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("Thread " + Thread.currentThread().getName() + " value " + i);
			}
			return i;
		}, newFixedThreadPool);
		supplyAsync2.join();
	}

}
