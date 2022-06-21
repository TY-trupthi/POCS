package com.tyss.jsoninmysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.jsoninmysql.dto.SuccessResponse;
import com.tyss.jsoninmysql.dto.UserDTO;
import com.tyss.jsoninmysql.service.UserDetailsService;

@RestController
@RequestMapping("/json")
public class UserController {
	
	@Autowired
	private UserDetailsService userService;

	@PostMapping("/save")
	public ResponseEntity<SuccessResponse> saveUser(@RequestBody UserDTO user) {
		UserDTO result = userService.saveUser(user);
		if (result != null) {
			return new ResponseEntity<>(new SuccessResponse(result, false, "Saved successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(result, true, "Operation failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<SuccessResponse> getAllUsers() {
		List<UserDTO> results = userService.getAllUsers();
		if (!results.isEmpty()) {
			return new ResponseEntity<>(new SuccessResponse(results, false, "Fetched successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(results, true, "No data found"),
					HttpStatus.NOT_FOUND);
		}
	}


	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> updateUser(@RequestBody UserDTO user) {
		UserDTO result = userService.updateUser(user);
		if (result != null) {
			return new ResponseEntity<>(new SuccessResponse(result, false, "updated successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new SuccessResponse(result, true, "Operation failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
