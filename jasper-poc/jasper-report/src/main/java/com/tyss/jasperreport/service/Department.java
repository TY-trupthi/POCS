package com.tyss.jasperreport.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "DEPARTMENT_TBL")
public class Department {
	
	    @Id
	    private int id;
	    private String departmentName;
	     
}
