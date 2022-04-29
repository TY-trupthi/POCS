package com.tyss.mongodb.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SQLUserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName;

	private Long mobile;

}
