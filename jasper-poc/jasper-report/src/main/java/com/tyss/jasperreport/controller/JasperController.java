package com.tyss.jasperreport.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.jasperreport.pojo.Employee;
import com.tyss.jasperreport.pojo.repo.EmployeeRepository;
import com.tyss.jasperreport.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class JasperController {
	@Autowired
    private EmployeeRepository repository;
    @Autowired
    private ReportService service;

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {

        return repository.findAll();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }



}
