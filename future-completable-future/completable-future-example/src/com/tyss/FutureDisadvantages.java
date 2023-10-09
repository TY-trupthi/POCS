package com.tyss;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDisadvantages {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		
		Future<List<Integer>> submit = newFixedThreadPool.submit(()-> {
			System.err.println(Thread.currentThread().getName());
			return Arrays.asList(1,2,3);
		});
		
		Future<List<Integer>> submit1 = newFixedThreadPool.submit(()-> {
			System.err.println(Thread.currentThread().getName());
			return Arrays.asList(4,5,6);
		});
		
		Future<List<Integer>> submit2 = newFixedThreadPool.submit(()-> {
			System.err.println(Thread.currentThread().getName());
			//There is no exception handling
			System.err.println(5/0);
			return Arrays.asList(4,5,6);
		});
		
		//we can not join the output
		
		//we can not reuse the output for other thread
		//until we do get the code in submit will not get executed
		List<Integer> list = submit2.get();
		
		System.err.println(list);
	}

}
