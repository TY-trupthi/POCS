package com.tyss.jasperreport.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class EmployeeDetails {

	private String name;
	private String designation;
	private double salary;
	private String doj;
	private String departmentName;

}
