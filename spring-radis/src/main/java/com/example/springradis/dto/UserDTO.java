package com.example.springradis.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String userName;

	private Long mobileNo;

}
