package com.tyss;

public class Product {

	private String productName;
	private Double cost;

	private void printMessage() {
		System.err.println("Trupthi");
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
