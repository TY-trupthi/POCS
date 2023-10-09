package com.tyss.dto;

public class Employee {
	
	private String name;
	
	private String emailId;
	
	private Boolean isActive;
	
	private Integer batchNumber;

	public Employee(String name, String emailId, Boolean isActive, Integer batchNumber) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.isActive = isActive;
		this.batchNumber = batchNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(Integer batchNumber) {
		this.batchNumber = batchNumber;
	}

}
