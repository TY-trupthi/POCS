package com.tyss.jasperreport.pojo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.jasperreport.service.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
