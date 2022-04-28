package com.tyss.adminclient.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class TestController {
	
	@GetMapping(path = "/test")
	public ResponseEntity<String> startBatch() {
		log.info("Info");
		log.warn("warning");
		return new ResponseEntity<String>("Tested", HttpStatus.OK);
	}


}
