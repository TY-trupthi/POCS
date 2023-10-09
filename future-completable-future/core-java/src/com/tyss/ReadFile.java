package com.tyss;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeSet;

public class ReadFile {

	public static void main(String[] args) {

		String fileName = "C:/Users/Trupthi/Desktop/file-to-read.txt";
		TreeSet<String> treeSet = new TreeSet<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.err.println(line);
				String[] split = line.split("\\s+");
				for (String string : split) {
					treeSet.add(string);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(treeSet);
	}

}
