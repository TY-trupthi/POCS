package com.tyss.contactmanager.dto;

import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDetailsDTO {

	private Long id;

	private String contactName;

	private String email;

	@Pattern(regexp = "\\d{10}", message = "Not a Valid Number")
	private String contactNumber;

}
