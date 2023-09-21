package com.tyss.annotation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.annotation.dto.EmployeeDetails;
import com.tyss.annotation.dto.SuccessResponse;

@RequestMapping("employee")
@RestController
public class EmployeeController {
	
	@PostMapping
	public ResponseEntity<SuccessResponse> saveEmployee(@RequestBody @Valid EmployeeDetails employeeDetails){
	  return new ResponseEntity<>(new SuccessResponse(employeeDetails, "Saved Successfully", Boolean.FALSE), HttpStatus.CREATED);
	}

}
