
package com.te.search.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.search.dto.RequestDTO;
import com.te.search.dto.SearchRequestDTO;
import com.te.search.dto.SuccessResponseDTO;
import com.te.search.service.SimpleFileUploadImpl;

@RestController

@RequestMapping("/resume")
public class FileUploadController {

	@Autowired
	private SimpleFileUploadImpl fileUploadServiceImpl;

	// .\bin\elasticsearch.bat
	//elastic search version - 7.16.2
	@PostMapping("/upload")
	public ResponseEntity<SuccessResponseDTO> upload(@RequestBody RequestDTO requestDTO) throws IOException {
		return new ResponseEntity<>(SuccessResponseDTO.builder().data(fileUploadServiceImpl.upload(requestDTO))
				.message("Uploaded Successfully").build(), HttpStatus.CREATED);
	}

	@GetMapping("/{documentId}")
	public ResponseEntity<SuccessResponseDTO> get(@PathVariable String documentId) throws IOException {
		return new ResponseEntity<>(SuccessResponseDTO.builder().data(fileUploadServiceImpl.getDocument(documentId))
				.message("Fetched Successfully").build(), HttpStatus.CREATED);
	}

	@PostMapping("/search")
	public ResponseEntity<SuccessResponseDTO> get(@RequestBody SearchRequestDTO searchRequestDTO) {
		return new ResponseEntity<>(SuccessResponseDTO.builder().data(fileUploadServiceImpl.anyMatch(searchRequestDTO))
				.message("Fetched Successfully").build(), HttpStatus.CREATED);
	}

}
