package com.tyss.centralizedlogging.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {
		
		@Pointcut(value="execution(* com.tyss.centralizedlogging.*.*.* (..))")
		public void myPointcut() {
			
		}
		
		@Around("myPointcut()")
		public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
			ObjectMapper mapper= new ObjectMapper();
			String methodName=pjp.getSignature().getName();
			String className=pjp.getTarget().getClass().toString();
			Object[] array=pjp.getArgs();
			log.info("method invoked, class name: "+className+", method name : "+methodName+", arguments : "+mapper.writeValueAsString(array));
			
			Object object=pjp.proceed();
			
			log.info("class name: "+className+", method name : "+methodName+", Response : "+mapper.writeValueAsString(object));
			
			return object;
		}
		
		@AfterThrowing(pointcut="execution(* com.tyss.centralizedlogging.*.*.* (..))", throwing="exception")
		public void logException(JoinPoint joinPoint, Exception exception) throws Throwable {
			ObjectMapper mapper= new ObjectMapper();
			String methodName=joinPoint.getSignature().getName();
			String className=joinPoint.getTarget().getClass().toString();
			Object[] array=joinPoint.getArgs();
			log.info("Exception Message : "+exception.getMessage()+", Exception Cause : "+exception.getCause()+", class name: "+className+", method name : "+methodName+", arguments : "+mapper.writeValueAsString(array));
		}

	

}
