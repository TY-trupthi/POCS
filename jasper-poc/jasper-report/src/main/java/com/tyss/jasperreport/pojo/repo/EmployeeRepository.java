package com.tyss.jasperreport.pojo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tyss.jasperreport.pojo.Employee;
import com.tyss.jasperreport.view.EmployeeDetails;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
}
