package com.tyss.mongodb.dto;

import java.io.Serializable;

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

}
