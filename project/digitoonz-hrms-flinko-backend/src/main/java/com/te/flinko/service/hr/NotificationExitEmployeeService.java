package com.te.flinko.service.hr;

import java.util.List;

import com.te.flinko.dto.hr.DiscussionFeedbackDTO;
import com.te.flinko.dto.hr.NotificationExitEmployeeDTO;
import com.te.flinko.dto.hr.NotificationExitInterviewDTO;
import com.te.flinko.dto.hr.NotificationExitInterviewDropdownDTO;
import com.te.flinko.dto.hr.UpdateFeedbackDTO;

public interface NotificationExitEmployeeService {

	// Api for saving the ExitInterview Schedule
	public NotificationExitInterviewDTO scheduleInterview(NotificationExitInterviewDTO scheduleInterviewdto,
			Long resignationId);

	// Api for fetching the employees who have applied for resignation for a company
	List<NotificationExitEmployeeDTO> resignationDetails(Long employeeInfoId, Long companyId);

	// Api for fetching the employees for organizers dropdown
	public List<NotificationExitInterviewDropdownDTO> getExitInterviewDropdowndtoList(Long companyId);

	// Api for fetching the details of an employees who have applied for resignation based on resignation id
	public NotificationExitEmployeeDTO exitEmployeedto(Long resignatinId);
	
	DiscussionFeedbackDTO discussionFeedbackInfo(Long discussionId, Long companyId);
	
	UpdateFeedbackDTO updateFeedback(Long discussionId, UpdateFeedbackDTO feedbackDto);

}
