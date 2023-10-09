package com.te.flinko.dto.hr;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class DiscussionFeedbackDTO {
	
	private Long companyId;
	private Long discussionId;
	private String employeeFullname;
	private LocalDate discussionDate;
	private List<String> feedbackFactor;

}
