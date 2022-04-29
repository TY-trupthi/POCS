package com.tyss.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.mongodb.dto.SuccessResponse;
import com.tyss.mongodb.dto.UserDTO;
import com.tyss.mongodb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<SuccessResponse> saveUser(@RequestBody UserDTO user) {
		UserDTO result = userService.saveUser(user);
		if (result != null) {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(result, false, "Saved successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(result, true, "Operation failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<SuccessResponse> getAllUsers() {
		List<UserDTO> results = userService.getAllUsers();
		if (!results.isEmpty()) {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(results, false, "Fetched successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(results, true, "No data found"),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<SuccessResponse> getUser(@PathVariable String userId) {
		UserDTO result = userService.getUser(userId);
		if (result != null) {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(result, false, "Fetched successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(result, true, "No data found"),
					HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> updateUser(@RequestBody UserDTO user) {
		UserDTO result = userService.updateUser(user);
		if (result != null) {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(result, false, "updated successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(result, true, "Operation failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable String userId) {
		boolean result = userService.deleteUser(userId);
		if (result) {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(null, result, "Deleted successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(null, result, "No data found"),
					HttpStatus.NOT_FOUND);
		}
	}

}