package com.tyss.actuatorexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestThreadController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String testHello() {
		new Thread("test") {
			public void run()  {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} .start();
		return "TEST OK";
	}

}
