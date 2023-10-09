package com.tyss;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableRunAsync {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		withExecutor();
		withOutExecutor();
	}
	
	public static void withOutExecutor() throws InterruptedException, ExecutionException {
		List<Integer> asList = Arrays.asList(1,2,3);
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(()->{ 
			System.err.println(Thread.currentThread().getName());
			asList.stream().forEach(System.out::println);
		});
		
		runAsync.get();
	}
	
	public static void withExecutor() throws InterruptedException, ExecutionException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		List<Integer> asList = Arrays.asList(1,2,3);
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(()->{ 
			System.err.println(Thread.currentThread().getName());
			asList.stream().forEach(System.out::println);
		}, newFixedThreadPool);
		
		runAsync.get();
	}

}
