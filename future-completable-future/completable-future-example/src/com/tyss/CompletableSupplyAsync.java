package com.tyss;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableSupplyAsync {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		withExecutor();
		withOutExecutor();
	}
	
	public static void withOutExecutor() throws InterruptedException, ExecutionException {
		 CompletableFuture<List<Integer>> supplyAsync = CompletableFuture.supplyAsync(()->{ 
			System.err.println(Thread.currentThread().getName());
			return Arrays.asList(1,2,3);
		});
		 
		 supplyAsync.get().stream().forEach(System.out::println);
	}
	
	public static void withExecutor() throws InterruptedException, ExecutionException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		 CompletableFuture<List<Integer>> supplyAsync = CompletableFuture.supplyAsync(()->{ 
				System.err.println(Thread.currentThread().getName());
				return Arrays.asList(1,2,3);
			}, newFixedThreadPool);
		 supplyAsync.get().stream().forEach(System.out::println);
	}
}
