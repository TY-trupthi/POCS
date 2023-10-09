package com.tyss;

import java.io.BufferedReader;
import java.io.FileReader;

public class ExcelReader {
	
	public static void main(String[] args) {
		String fileName = "C:/Users/Trupthi/Desktop/Book1.csv";
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
			String line;
			while ((line = bufferedReader.readLine())!=null) {
				String[] split = line.split(",");
				for (String string : split) {
					System.err.print(string + " ");
				}
				System.err.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
