package com.tyss.schedulemail.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.schedulemail.service.MailService;
import com.tyss.schedulemail.view.SuccessResponse;


@RestController
@RequestMapping(path = "/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@PostMapping(path = "/send")
	public ResponseEntity<SuccessResponse> startBatch() {
		mailService.sendMail();
		return new ResponseEntity<SuccessResponse>(new SuccessResponse("Success", false), HttpStatus.OK);		
	}
	
	

}
