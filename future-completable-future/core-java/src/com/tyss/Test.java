package com.tyss;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {

		System.err.println("Came");

		Class<Product> productClass = Product.class;

		Product product = new Product();

		Field[] fields = productClass.getDeclaredFields();

		System.err.println(fields.length);
		Arrays.asList(fields).stream().forEach((field) -> System.err.println(field.getName()));

		Employee employee = new Employee("Alice", "Alice@mailinator.com");
		System.err.println(employee);
		System.err.println(employee.name());

		Method[] declaredMethods = productClass.getDeclaredMethods();
		for (int i = 0; i < declaredMethods.length; i++) {
			if (declaredMethods[i].getName().equalsIgnoreCase("printMessage")) {
				Method m = declaredMethods[i];
				m.setAccessible(true);
				m.invoke(product);
			}
		}

		Method declaredMethod = productClass.getDeclaredMethod("printMessage");
		declaredMethod.setAccessible(true);
		declaredMethod.invoke(product);

//		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class<Product> forName = Product.class;
		Product dynamicObject = (Product) forName.newInstance();
		System.err.println(dynamicObject.getProductName());
//		Test test = new Test();
//		test = null;
//		
//		Test test1 = new Test();
//		test1 = null;

		ImmutableClass immutableClass = new ImmutableClass("Trupthi", "Kale");
		System.err.println("Imm " + immutableClass);
		immutableClass = new ImmutableClass("Anu", "M");

		System.err.println("Imm " + immutableClass);

		Runtime runtime = Runtime.getRuntime();

		System.err.println(runtime.freeMemory());

		String s1 = "Hello Word";
		String s2 = "Hello Word";
		System.err.println(s1 == s2);

		
		List<Integer> newList = Arrays.asList(1, 2, 3, 4, 7, 6,6);
		
		newList = new CopyOnWriteArrayList<>(newList);
		
		
		for (Integer integer : newList) {
			if (integer == 2)
				newList.remove(integer);
		}

		System.gc();
		
		TreeSet<Integer> sortedUniqueValue = new TreeSet<>(newList);
		System.err.println(sortedUniqueValue);
		
		//List<Integer> emptyList = Collections.emptyList();
		//emptyList.add(1);
		
		List<Integer> check1 = new ArrayList<>();
		check1.add(1);
		
		Iterator<Integer> listIterator = check1.iterator();
		
		while (listIterator.hasNext()) {
			System.err.println(listIterator.next());
		}
		
		System.err.println(check1);
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Mongo");
        list.add("Orange");
        list.add("kivi");

        // Create a ListIterator starting at index 0
        ListIterator<String> listIterator1 = list.listIterator();

        // Traverse the list and add an element after the first element
        while (listIterator1.hasNext()) {
            String element = listIterator1.next();
            if (element.contains("i")) {
                listIterator1.remove();  // Adds "D" after "A"
            }
        }

        // Print the modified list
        System.out.println("Modified list: " + list);
	}

	@Override
	protected void finalize() {
		System.err.println("came" + this);
	}

}
