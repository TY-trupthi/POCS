package com.tyss.annotation.dto;

import javax.validation.constraints.NotBlank;

import com.tyss.annotation.util.ValidStatus;

import lombok.Data;

@Data
public class EmployeeDetails {
	
	private Integer id;
	
	@NotBlank
	private String name;
	
	@ValidStatus
	private String status;

}
