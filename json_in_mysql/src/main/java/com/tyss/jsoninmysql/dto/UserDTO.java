package com.tyss.jsoninmysql.dto;

import lombok.Data;

@Data
public class UserDTO {

	 private int userId;
	 
	 private String name;
	 
	 private Object otherDetails;
}
