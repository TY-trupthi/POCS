package com.te.flinko.service.hr;

import static com.te.flinko.common.hr.HrConstants.COMPANY_INFORMATION_NOT_PRESENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.flinko.dto.employee.MailDto;
import com.te.flinko.dto.hr.DiscussionFeedbackDTO;
import com.te.flinko.dto.hr.NotificationExitEmployeeDTO;
import com.te.flinko.dto.hr.NotificationExitInterviewDTO;
import com.te.flinko.dto.hr.NotificationExitInterviewDropdownDTO;
import com.te.flinko.dto.hr.UpdateFeedbackDTO;
import com.te.flinko.entity.admin.CompanyInfo;
import com.te.flinko.entity.employee.CompanyEmployeeResignationDetails;
import com.te.flinko.entity.employee.EmployeeAnnualSalary;
import com.te.flinko.entity.employee.EmployeeOfficialInfo;
import com.te.flinko.entity.employee.EmployeePersonalInfo;
import com.te.flinko.entity.employee.EmployeeReportingInfo;
import com.te.flinko.entity.employee.EmployeeResignationDiscussion;
import com.te.flinko.entity.hr.CandidateInterviewInfo;
import com.te.flinko.entity.hr.mongo.FeedbackConfiguration;
import com.te.flinko.exception.DataNotFoundException;
import com.te.flinko.exception.hr.CompanyNotFoundException;
import com.te.flinko.exception.hr.CustomExceptionForHr;
import com.te.flinko.repository.admin.CompanyInfoRepository;
import com.te.flinko.repository.employee.CompanyEmployeeResignationDetailsRepository;
import com.te.flinko.repository.employee.EmployeeAnnualSalaryRepository;
import com.te.flinko.repository.employee.EmployeePersonalInfoRepository;
import com.te.flinko.repository.employee.EmployeeReportingInfoRepository;
import com.te.flinko.repository.employee.EmployeeResignationDiscussionRepository;
import com.te.flinko.repository.employee.ReportingManagerRepository;
import com.te.flinko.repository.hr.mongo.FeedbackConfigurationRepository;
import com.te.flinko.service.mail.employee.EmailService;
import com.te.flinko.service.notification.employee.InAppNotificationServiceImpl;
import com.te.flinko.service.notification.employee.PushNotificationService;

@Service
public class NotificationExitEmployeeServiceImpl implements NotificationExitEmployeeService {

	@Autowired
	private EmployeeResignationDiscussionRepository employeeScheduleInterviewRepository;

	@Autowired
	private CompanyEmployeeResignationDetailsRepository exitEmployeeListRepository;

	@Autowired
	private EmployeePersonalInfoRepository personalInfo;

	@Autowired
	private ReportingManagerRepository reportingRepository;

	@Autowired
	private CompanyInfoRepository companyInfoRepository;

	@Autowired
	private EmployeeReportingInfoRepository infoRepository;

	@Autowired
	private EmployeeAnnualSalaryRepository employeeAnnualSalaryRepository;

	@Autowired
	private InAppNotificationServiceImpl notificationServiceImpl;

	@Autowired
	private PushNotificationService pushNotificationService;

	@Autowired
	private FeedbackConfigurationRepository feedbackConfigurationRepository;

	@Autowired
	private EmailService emailService;

	// Api for saving the ExitInterview Schedule

	@Override
	public NotificationExitInterviewDTO scheduleInterview(NotificationExitInterviewDTO scheduleInterviewdto,
			Long resignationId) {

		CompanyEmployeeResignationDetails optionalResignationDetails = exitEmployeeListRepository
				.findById(resignationId).orElseThrow(() -> new DataNotFoundException("Resignation Details Not Found"));

		if (optionalResignationDetails != null) {

			EmployeeResignationDiscussion resignationDiscussions = new EmployeeResignationDiscussion();

			BeanUtils.copyProperties(scheduleInterviewdto, resignationDiscussions);
			resignationDiscussions.setStatus("Pending");

			List<Long> employeeInfoIdList = scheduleInterviewdto.getEmployeeInfoIdList();
			if (employeeInfoIdList == null || employeeInfoIdList.isEmpty()) {
				throw new DataNotFoundException("Attendees information cannot be empty");
			}

			List<EmployeePersonalInfo> employeePersonalInfo = personalInfo.findAllById(employeeInfoIdList);

			employeePersonalInfo.forEach(pi -> {
				List<EmployeeResignationDiscussion> listtemp = pi.getEmployeeResignationDiscussionList();
				listtemp.add(resignationDiscussions);
				pi.setEmployeeResignationDiscussionList(listtemp);
				notificationServiceImpl.saveNotification(
						"Exit discussion for " + optionalResignationDetails.getEmployeePersonalInfo().getFirstName()
								+ " " + optionalResignationDetails.getEmployeePersonalInfo().getLastName()
								+ " is scheduled on " + resignationDiscussions.getDiscussionDate(),
						pi.getEmployeeInfoId());

				if (pi.getExpoToken() != null) {
					pushNotificationService.pushMessage("Flinko",
							"Exit discussion for " + optionalResignationDetails.getEmployeePersonalInfo().getFirstName()
									+ " " + optionalResignationDetails.getEmployeePersonalInfo().getLastName()
									+ " is scheduled on " + resignationDiscussions.getDiscussionDate(),
							pi.getExpoToken());
				}
			});

			resignationDiscussions.setEmployeePersonalInfoList(employeePersonalInfo);
			resignationDiscussions.setCompanyEmployeeResignationDetails(optionalResignationDetails);

			EmployeeResignationDiscussion save = employeeScheduleInterviewRepository.save(resignationDiscussions);

			BeanUtils.copyProperties(save, scheduleInterviewdto);

			sendMailToEmployee(resignationDiscussions, optionalResignationDetails.getEmployeePersonalInfo());
			save.getEmployeePersonalInfoList().stream().forEach(employee -> sendMailToOrganizers(resignationDiscussions,
					employee, scheduleInterviewdto.getLinkURL()));

			notificationServiceImpl.saveNotification(
					"Exit discussion is scheduled on " + resignationDiscussions.getDiscussionDate(),
					optionalResignationDetails.getEmployeePersonalInfo().getEmployeeInfoId());
			if (optionalResignationDetails.getEmployeePersonalInfo().getExpoToken() != null) {
				pushNotificationService.pushMessage("Flinko",
						"Exit discussion is scheduled on " + resignationDiscussions.getDiscussionDate(),
						optionalResignationDetails.getEmployeePersonalInfo().getExpoToken());
			}
			return scheduleInterviewdto;

		} else {
			throw new DataNotFoundException("ResignationId Not Present");
		}

	}

	private void sendMailToEmployee(EmployeeResignationDiscussion employeeResignationDiscussion,
			EmployeePersonalInfo employeePersonalInfo) {
		EmployeeOfficialInfo employeeOfficialInfo = employeePersonalInfo.getEmployeeOfficialInfo();
		if (employeeOfficialInfo != null && employeeOfficialInfo.getOfficialEmailId() != null) {
			MailDto mailDtoForEmployee = new MailDto();
			mailDtoForEmployee.setTo(employeeOfficialInfo.getOfficialEmailId());
			mailDtoForEmployee.setSubject("Exit Interview Invitation");
			mailDtoForEmployee.setBody("Dear" + " " + employeePersonalInfo.getFirstName() + " "
					+ employeePersonalInfo.getLastName() + "," + "<BR />" + "<BR />"
					+ "This is to inform you that Exit Interview has been scheduled for you. <body>\n" + "<body>\n"
					+ "Please find the details of interview below:" + "<body>\n" + "<body>\n" + "<body>\n"
					+ "Interview Date :-" + employeeResignationDiscussion.getDiscussionDate() + "<BR />"
					+ "Interview type :-" + employeeResignationDiscussion.getDiscussionType() + "<BR />"
					+ "Interview details :-" + employeeResignationDiscussion.getDiscussionDetails() + "<BR />"
					+ "<BR />" + "Interview start time :-" + employeeResignationDiscussion.getStartTime() + "<BR />"
					+ "Duration :-" + employeeResignationDiscussion.getDuration() + "<BR />" + "<BR />" + "<BR />"
					+ "Thanks and Regards" + "<BR />" + "Team " + employeePersonalInfo.getCompanyInfo().getCompanyName()
					+ "</body>\n" + "</html>");
			emailService.sendMailWithLink(mailDtoForEmployee);
		}
	}

	private Boolean sendMailToOrganizers(EmployeeResignationDiscussion employeeResignationDiscussion,
			EmployeePersonalInfo employeePersonalInfo, String linkURL) {
		MailDto mailDto = new MailDto();
		if (employeePersonalInfo != null) {
			EmployeeOfficialInfo employeeOfficialInfo = employeePersonalInfo.getEmployeeOfficialInfo();
			if (employeeOfficialInfo != null && employeeOfficialInfo.getOfficialEmailId() != null) {
				mailDto.setTo(employeeOfficialInfo.getOfficialEmailId());
				mailDto.setSubject("Exit Interview Invitation");
				mailDto.setBody("<html>\n" + "<body>\n" + "\n"
						+ "This is to inform that you have to take an Exit Interview. Please find the employee details below:"
						+ "<BR />" + "<BR />" + "<BR />" + "Name of employee :-" + employeePersonalInfo.getFirstName()
						+ " " + employeePersonalInfo.getLastName() + "<BR />" + "Interview type :-"
						+ employeeResignationDiscussion.getDiscussionType() + "<BR />" + "Interview details :-"
						+ employeeResignationDiscussion.getDiscussionDetails() + "<BR />" + "Interview Date :-"
						+ employeeResignationDiscussion.getDiscussionDate() + "<BR />" + "Interview start time :-"
						+ employeeResignationDiscussion.getStartTime() + "<BR />" + "Duration :-"
						+ employeeResignationDiscussion.getDuration() + "<BR />" + "Employee email id :-"
						+ employeeOfficialInfo.getOfficialEmailId() + "<BR />" + "Employee mobile no :-"
						+ employeePersonalInfo.getMobileNumber() + "<BR />"
						+ "Kindly take it forward. Once the interview is completed, provide the feedback using below form :"
						+ "<BR />" + "<a href='" + linkURL + "/" + employeePersonalInfo.getCompanyInfo().getCompanyId()
						+ "/" + employeeResignationDiscussion.getDuscussionId() + "/"
						+ employeePersonalInfo.getEmployeeInfoId() + "'>" + "Feedback Form" + "</a>" + "<BR />"
						+ "<BR />" + "Thanks and Regards," + "<BR />" + "Team "
						+ employeePersonalInfo.getCompanyInfo().getCompanyName() + "</body>\n" + "</html>");
				emailService.sendMailWithLink(mailDto);
			}
		}
		return true;
	}

	// Api for fetching the employees who have applied for resignation for a
	// company

	@Override
	public List<NotificationExitEmployeeDTO> resignationDetails(Long employeeInfoId, Long companyId) {

		List<Long> employeeInfoIdList = infoRepository.findByReportingHREmployeeInfoId(employeeInfoId).stream()
				.map(info -> info.getEmployeePersonalInfo().getEmployeeInfoId()).collect(Collectors.toList());

		List<CompanyEmployeeResignationDetails> resignationDetailsList = exitEmployeeListRepository
				.findByStatusIgnoreCaseAndCompanyInfoCompanyIdAndEmployeePersonalInfoEmployeeInfoIdIn("Approved",
						companyId, employeeInfoIdList);

		List<NotificationExitEmployeeDTO> exitEmployeedtos = new ArrayList<>();

		for (CompanyEmployeeResignationDetails companyEmployeeResignationDetails : resignationDetailsList) {
			if (companyEmployeeResignationDetails.getEmployeeResignationDiscussionList().isEmpty()) {

				NotificationExitEmployeeDTO employeedto = new NotificationExitEmployeeDTO();

				EmployeePersonalInfo employeePersonalInfo = companyEmployeeResignationDetails.getEmployeePersonalInfo();
				if (employeePersonalInfo != null) {

					EmployeeOfficialInfo employeeOfficialInfo = employeePersonalInfo.getEmployeeOfficialInfo();
					if (employeeOfficialInfo != null) {

						employeedto.setResignationId(companyEmployeeResignationDetails.getResignationId());

						employeedto.setEmployeeId(employeeOfficialInfo.getEmployeeId());
						employeedto.setFullName(
								employeePersonalInfo.getFirstName() + " " + employeePersonalInfo.getLastName());
						employeedto.setOfficialEmailId(employeeOfficialInfo.getOfficialEmailId());
						employeedto.setDepartment(employeeOfficialInfo.getDepartment());
						employeedto.setDesignation(employeeOfficialInfo.getDesignation());
						List<EmployeeReportingInfo> reportingMangerInfoList = reportingRepository
								.findByEmployeePersonalInfo(
										companyEmployeeResignationDetails.getEmployeePersonalInfo());
						String reportingManagerName = null;
						if (reportingMangerInfoList.size() > 1) {
							EmployeeReportingInfo employeeReportingInfo = reportingMangerInfoList
									.get(reportingMangerInfoList.size() - 1);

							reportingManagerName = (employeeReportingInfo.getReportingManager() == null) ? null
									: employeeReportingInfo.getReportingManager().getFirstName() + " "
											+ employeeReportingInfo.getReportingManager().getLastName();
						} else {
							EmployeeReportingInfo employeeReportingInfo = reportingMangerInfoList.get(0);
							reportingManagerName = (employeeReportingInfo.getReportingManager() == null) ? null
									: employeeReportingInfo.getReportingManager().getFirstName() + " "
											+ employeeReportingInfo.getReportingManager().getLastName();
						}

						employeedto.setReportingManager(reportingManagerName);
						employeedto.setMobileNumber(employeePersonalInfo.getMobileNumber());

						exitEmployeedtos.add(employeedto);
					}
				}
			}
		}
		return exitEmployeedtos;

	}

	// Api for fetching the employees for organizers dropdown
	@Override
	public List<NotificationExitInterviewDropdownDTO> getExitInterviewDropdowndtoList(Long companyId) {

		Optional<CompanyInfo> optionalDetails = companyInfoRepository.findById(companyId);
		if (optionalDetails.isPresent()) {

			List<NotificationExitInterviewDropdownDTO> companyEmployeeList = new ArrayList<>();

			List<EmployeePersonalInfo> employeePersonalInfos = personalInfo.findByCompanyInfoCompanyId(companyId);
			if (!employeePersonalInfos.isEmpty()) {

				for (EmployeePersonalInfo employeePersonalInfo : employeePersonalInfos) {

					NotificationExitInterviewDropdownDTO companyEmployee = new NotificationExitInterviewDropdownDTO();

					companyEmployee.setEmployeeId(employeePersonalInfo.getEmployeeOfficialInfo().getEmployeeId());
					companyEmployee
							.setEmployeeName(employeePersonalInfo.getFirstName() + employeePersonalInfo.getLastName());
					companyEmployee.setEmployeeInfoId(employeePersonalInfo.getEmployeeInfoId());

					companyEmployeeList.add(companyEmployee);
				}

				return companyEmployeeList;
			}
		}
		throw new DataNotFoundException("Comapany Id Not present");

	}

	// Api for fetching the details of employees who have applied for resignation
	@Override
	public NotificationExitEmployeeDTO exitEmployeedto(Long resignatinId) {
		Optional<CompanyEmployeeResignationDetails> optionalDetails = exitEmployeeListRepository.findById(resignatinId);
		if (optionalDetails.isPresent()) {

			CompanyEmployeeResignationDetails employeeResignationDiscussion = optionalDetails.get();

			EmployeePersonalInfo employeePersonalInfo = employeeResignationDiscussion.getEmployeePersonalInfo();
			if (employeePersonalInfo == null) {
				throw new DataNotFoundException("Employee Details Not Found");
			}

			EmployeeOfficialInfo employeeOfficialInfo = employeePersonalInfo.getEmployeeOfficialInfo();

			if (employeeOfficialInfo == null) {
				throw new DataNotFoundException("Employee Oficial Details Not Found");
			}

			NotificationExitEmployeeDTO exitEmployeedto = new NotificationExitEmployeeDTO();
			exitEmployeedto.setResignationId(employeeResignationDiscussion.getResignationId());

			exitEmployeedto.setEmployeeId(employeeOfficialInfo.getEmployeeId());
			exitEmployeedto.setFullName(employeePersonalInfo.getFirstName() + " " + employeePersonalInfo.getLastName());
			exitEmployeedto.setOfficialEmailId(employeeOfficialInfo.getOfficialEmailId());
			exitEmployeedto.setDepartment(employeeOfficialInfo.getDepartment());
			exitEmployeedto.setDesignation(employeeOfficialInfo.getDesignation());

			exitEmployeedto.setResignationReason(employeeResignationDiscussion.getResignationReason());
			List<EmployeeReportingInfo> reportingMangerInfoList = reportingRepository
					.findByEmployeePersonalInfo(employeePersonalInfo);
			String reportingManagerName = null;
			if (reportingMangerInfoList.size() > 1) {
				EmployeeReportingInfo employeeReportingInfo = reportingMangerInfoList
						.get(reportingMangerInfoList.size() - 1);
				reportingManagerName = (employeeReportingInfo.getReportingManager() == null) ? null
						: employeeReportingInfo.getReportingManager().getFirstName() + " "
								+ employeeReportingInfo.getReportingManager().getLastName();
			} else {
				EmployeeReportingInfo employeeReportingInfo = reportingMangerInfoList.get(0);
				reportingManagerName = (employeeReportingInfo.getReportingManager() == null) ? null
						: employeeReportingInfo.getReportingManager().getFirstName() + " "
								+ employeeReportingInfo.getReportingManager().getLastName();
			}
			exitEmployeedto.setReportingManager(reportingManagerName);
			exitEmployeedto.setMobileNumber(employeePersonalInfo.getMobileNumber());

			EmployeeAnnualSalary annualSalary = employeeAnnualSalaryRepository
					.findByEmployeePersonalInfo(employeePersonalInfo);
			exitEmployeedto.setAnnualSalary(annualSalary != null ? annualSalary.getAnnualSalary() : new BigDecimal(0));

			return exitEmployeedto;

		} else {
			throw new DataNotFoundException("ResignationId Not Found");
		}

	}

	@Override
	public DiscussionFeedbackDTO discussionFeedbackInfo(Long discussionId, Long companyId) {
		CompanyInfo companyInfo = companyInfoRepository.findByCompanyId(companyId)
				.orElseThrow(() -> new CompanyNotFoundException(COMPANY_INFORMATION_NOT_PRESENT));
		EmployeeResignationDiscussion disscussionDetails = employeeScheduleInterviewRepository.findById(discussionId)
				.orElseThrow(() -> new DataNotFoundException("Disscussion Details Not Found"));
		List<FeedbackConfiguration> feedbackList = feedbackConfigurationRepository
				.findByCompanyIdAndFeedbackType(companyInfo.getCompanyId(), "exit");
		if (feedbackList.isEmpty()) {
			throw new CustomExceptionForHr("Feedback Factor Not Found");
		}
		CompanyEmployeeResignationDetails companyEmployeeResignationDetails = disscussionDetails
				.getCompanyEmployeeResignationDetails();
		if (companyEmployeeResignationDetails == null) {
			throw new CustomExceptionForHr("Resignation Details Not Found");
		}
		EmployeePersonalInfo employeePersonalInfo = companyEmployeeResignationDetails.getEmployeePersonalInfo();
		if (employeePersonalInfo == null) {
			throw new CustomExceptionForHr("Employee Not Found");
		}
		DiscussionFeedbackDTO discussionFeedbackDTO = new DiscussionFeedbackDTO();
		discussionFeedbackDTO.setCompanyId(companyInfo.getCompanyId());
		discussionFeedbackDTO
				.setEmployeeFullname(employeePersonalInfo.getFirstName() + " " + employeePersonalInfo.getLastName());
		discussionFeedbackDTO.setDiscussionDate(disscussionDetails.getDiscussionDate());
		discussionFeedbackDTO.setDiscussionId(discussionId);
		discussionFeedbackDTO.setFeedbackFactor(feedbackList.get(0).getFeedbackFactor());
		return discussionFeedbackDTO;

	}

	@Override
	@Transactional
	public UpdateFeedbackDTO updateFeedback(Long discussionId, UpdateFeedbackDTO feedbackDto) {

		EmployeeResignationDiscussion disscussionDetails = employeeScheduleInterviewRepository.findById(discussionId)
				.orElseThrow(() -> new DataNotFoundException("Disscussion Details Not Found"));

		if (!disscussionDetails.getFeedback().isEmpty()) {
			throw new CustomExceptionForHr("Feedback Already Submitted");
		}
		disscussionDetails
				.setStatus(feedbackDto.getFeedback().get("overall feedback") == null ? disscussionDetails.getStatus()
						: feedbackDto.getFeedback().get("overall feedback"));
		disscussionDetails.setFeedback(feedbackDto.getFeedback());
		employeeScheduleInterviewRepository.save(disscussionDetails);
		return feedbackDto;

	}

}