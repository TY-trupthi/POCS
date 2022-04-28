package com.tyss.batchprocessing.service;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Started date and time"+ new Date());
		System.out.println("Status of starting"+ jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("End date and time");
		System.out.println("Status of ending"+ jobExecution.getStatus());
	}

}
