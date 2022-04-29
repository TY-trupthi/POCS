package com.tyss.mongodb.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import lombok.Data;

@Data
public class UserDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String userName;
	
	private Long mobileNo;
	
	private Map<String, String> others;
	
	private List<String> roles;

}
