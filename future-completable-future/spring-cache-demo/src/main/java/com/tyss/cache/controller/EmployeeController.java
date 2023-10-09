package com.tyss.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.cache.dto.EmployeeDTO;
import com.tyss.cache.dto.ResponseDTO;
import com.tyss.cache.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<ResponseDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.addEmployee(employeeDTO);
		return new ResponseEntity<>(new ResponseDTO(Boolean.FALSE, employeeDTO, "Added Successfully"),
				HttpStatus.CREATED);

	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<ResponseDTO> getEmployee(@PathVariable Integer employeeId) {
		return new ResponseEntity<>(
				new ResponseDTO(Boolean.FALSE, employeeService.getEmployee(employeeId), "Fetched Successfully"),
				HttpStatus.OK);

	}

}
