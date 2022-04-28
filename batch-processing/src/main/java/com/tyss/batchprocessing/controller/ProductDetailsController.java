package com.tyss.batchprocessing.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.batchprocessing.config.view.SuccessResponse;

@RestController
@RequestMapping(path = "/batch")
public class ProductDetailsController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@PostMapping(path = "/start")
	public ResponseEntity<SuccessResponse> startBatch() {
		JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
				.toJobParameters();
		try {
			jobLauncher.run(job, jobParameters);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Success", false), HttpStatus.OK);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {

			e.printStackTrace();
			return new ResponseEntity<SuccessResponse>(new SuccessResponse(e.getMessage(), true),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
