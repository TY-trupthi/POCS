package com.tyss.contactmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.contactmanager.dto.ContactDetailsDTO;
import com.tyss.contactmanager.dto.SuccessResponse;
import com.tyss.contactmanager.service.ContactDetailsServiceImpl;

@RestController
@RequestMapping("/contact")
@CrossOrigin("*")
public class ContactDetailsController {

	@Autowired
	private ContactDetailsServiceImpl contactDetailsServiceImpl;

	@GetMapping
	public ResponseEntity<SuccessResponse> getAll() {
		return ResponseEntity.ok().body(SuccessResponse.builder().data(contactDetailsServiceImpl.getAll())
				.message("Fetched Successfully").error(Boolean.FALSE).build());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<SuccessResponse> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok().body(SuccessResponse.builder().data(contactDetailsServiceImpl.getByEmail(email))
				.message("Fetched Successfully").error(Boolean.FALSE).build());
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse> save(@Valid @RequestBody ContactDetailsDTO contactDetailsDTO) {
		System.err.println("Helllllll");
		return ResponseEntity.ok().body(SuccessResponse.builder().data(contactDetailsServiceImpl.save(contactDetailsDTO))
				.message("Save Successfully").error(Boolean.FALSE).build());
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<SuccessResponse> deleteByEmail(@PathVariable String email) {
		return ResponseEntity.ok().body(SuccessResponse.builder().data(contactDetailsServiceImpl.deleteByEmail(email))
				.message("Deleted Successfully").error(Boolean.FALSE).build());
	}
	
	@PutMapping("/{email}")
	public ResponseEntity<SuccessResponse> updateByEmail(@PathVariable String email, @RequestBody ContactDetailsDTO contactDetailsDTO) {
		return ResponseEntity.ok().body(SuccessResponse.builder().data(contactDetailsServiceImpl.updateByEmail(email, contactDetailsDTO))
				.message("Updated Successfully").error(Boolean.FALSE).build());
	}

}
