package com.tyss;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.tyss.dto.Employee;

public class CompletableMethods {

	public static void main(String[] args) {
		Map<Integer, List<Employee>> mainList = new LinkedHashMap<>();
		ArrayList<Employee> arrayList = new ArrayList<>();
		arrayList.add(new Employee("Trupthi", "trupthi.a@mailinator.com", Boolean.TRUE,1));
		arrayList.add(new Employee("Anandhi", "anandhi.a@mailinator.com", Boolean.TRUE,1));
		ArrayList<Employee> arrayList1 = new ArrayList<>();
		arrayList.add(new Employee("Harini", "harini.a@mailinator.com", Boolean.TRUE,2));
		arrayList.add(new Employee("Vandana", "vandana.a@mailinator.com", Boolean.TRUE,2));
		ArrayList<Employee> arrayList2 = new ArrayList<>();
		arrayList2.add(new Employee("Anu", "anu.a@mailinator.com", Boolean.TRUE,2));
		arrayList2.add(new Employee("Manu", "manu.a@mailinator.com", Boolean.TRUE,2));
		ArrayList<Employee> arrayList3 = new ArrayList<>();
		arrayList2.add(new Employee("Guru", "guru.a@mailinator.com", Boolean.TRUE,2));
		arrayList2.add(new Employee("jani", "jani.a@mailinator.com", Boolean.TRUE,2));
		mainList.put(1, arrayList);
		mainList.put(2, arrayList1);
		mainList.put(3, arrayList2);
		mainList.put(4, arrayList3);
	    employeeProcessor(mainList);
	}

	public static void employeeProcessor(Map<Integer,List<Employee>> mainList) {
		System.err.println(mainList.size());
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
		for (Entry<Integer,List<Employee>> arrayList : mainList.entrySet()) {
			
			CompletableFuture.supplyAsync(() -> {
				System.err.println("Get " + Thread.currentThread().getName() + " batch " + arrayList.getKey());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return arrayList.getValue();
			},newFixedThreadPool ).thenApplyAsync(employees-> {
				System.err.println("Filter " + Thread.currentThread().getName() + " size " + employees.size()
				);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return employees.stream().filter(employee -> employee.getIsActive().equals(Boolean.TRUE))
						.collect(Collectors.toList());
			},newFixedThreadPool ).thenApplyAsync((employees) -> {
				System.err.println("Fetch email " + Thread.currentThread().getName() + " size " + employees.size());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return employees.stream().map(Employee::getEmailId).collect(Collectors.toList());
			},newFixedThreadPool ).thenAcceptAsync(emails -> {
				System.err.println("Print " + Thread.currentThread().getName() +" size " + emails.size());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				emails.stream().forEach((email) -> System.out.println(email));
			},newFixedThreadPool );
		}
		
		
		
	}
	


}
