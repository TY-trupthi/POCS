package com.tyss.parametersinheader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	private HttpServletRequest request;
	
	public Long getId() {
	  String id = request != null ? request.getHeader("id") : "";
	 
	  Long finalId = null;
	  if(id!=null && !id.equals("")) {
		  finalId = Long.parseLong(id);
	  }
	return finalId;
	}

}
