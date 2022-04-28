package com.tyss.jasperreport.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.tyss.jasperreport.pojo.Employee;
import com.tyss.jasperreport.pojo.repo.DepartmentRepository;
import com.tyss.jasperreport.pojo.repo.EmployeeRepository;
import com.tyss.jasperreport.view.EmployeeDetails;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private DepartmentRepository deptRepo;

	/*
	 * public String exportReport(String reportFormat) throws FileNotFoundException,
	 * JRException { String path = "D:\\"; List<Employee> employees =
	 * repository.findAll(); // employees.forEach(System.out::println); //load file
	 * and compile it //File file = ResourceUtils.getFile("classpath:test1.jrxml");
	 * File file = ResourceUtils.getFile("classpath:test20.jrxml"); JasperReport
	 * jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	 * JRBeanCollectionDataSource dataSource = new
	 * JRBeanCollectionDataSource(employees); Map<String, Object> parameters = new
	 * HashMap<>(); parameters.put("createdBy", "Trupthi");
	 * parameters.put("Parameter1",dataSource ); JasperPrint jasperPrint =
	 * JasperFillManager.fillReport(jasperReport, parameters, dataSource); if
	 * (reportFormat.equalsIgnoreCase("html")) {
	 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
	 * "\\employees.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
	 * JasperExportManager.exportReportToPdfFile(jasperPrint, path +
	 * "\\employees"+LocalDate.now()+".pdf"); }
	 * 
	 * return "report generated in path : " + path; }
	 */
	/*
	 * public String exportReport(String reportFormat) throws FileNotFoundException,
	 * JRException { String path = "D:\\"; List<Employee> employees =
	 * repository.findAll(); List<EmployeeDetails> employeeDetails = new
	 * ArrayList<>(); for (Employee employee : employees) { EmployeeDetails
	 * employeeDetail = new EmployeeDetails(); BeanUtils.copyProperties(employee,
	 * employeeDetail); Optional<Department> dept=
	 * deptRepo.findById(employee.getDepartment_id());
	 * employeeDetail.setDepartmentName(dept.get().getDepartmentName());
	 * employeeDetails.add(employeeDetail); } //
	 * employees.forEach(System.out::println); //load file and compile it //File
	 * file = ResourceUtils.getFile("classpath:test1.jrxml"); File file =
	 * ResourceUtils.getFile("classpath:test21.jrxml"); JasperReport jasperReport =
	 * JasperCompileManager.compileReport(file.getAbsolutePath());
	 * JRBeanCollectionDataSource dataSource = new
	 * JRBeanCollectionDataSource(employeeDetails); Map<String, Object> parameters =
	 * new HashMap<>(); parameters.put("createdBy", "Trupthi");
	 * parameters.put("Parameter1",dataSource ); JasperPrint jasperPrint =
	 * JasperFillManager.fillReport(jasperReport, parameters, dataSource); if
	 * (reportFormat.equalsIgnoreCase("html")) {
	 * JasperExportManager.exportReportToHtmlFile(jasperPrint, path +
	 * "\\employeedetails.html"); } if (reportFormat.equalsIgnoreCase("pdf")) {
	 * JasperExportManager.exportReportToPdfFile(jasperPrint, path +
	 * "\\employeedetails"+LocalDate.now()+".pdf"); }
	 * 
	 * return "report generated in path : " + path; }
	 */
	

	  public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
	        String path = "D:\\";
	        List<Employee> employees = repository.findAll();
	        List<Department> departments = deptRepo.findAll();
	       
//	        employees.forEach(System.out::println);
	        //load file and compile it
	        //File file = ResourceUtils.getFile("classpath:test1.jrxml");
	        File file = ResourceUtils.getFile("classpath:test21.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Trupthi");
	        parameters.put("Parameter1",dataSource );
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (reportFormat.equalsIgnoreCase("html")) {
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employeedetails.html");
	        }
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employeedetails"+LocalDate.now()+".pdf");
	        }

	        return "report generated in path : " + path;
	    }

}
