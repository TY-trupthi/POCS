package com.tyss.mongodb.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Id;

	private String userName;

	private Long mobileNo;
	
	private Map<String, String> others;
	
	private List<String> roles;

}
