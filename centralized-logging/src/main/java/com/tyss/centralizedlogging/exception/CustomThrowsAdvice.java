/*
 * package com.tyss.centralizedlogging.exception;
 * 
 * import org.springframework.aop.ThrowsAdvice; import
 * org.springframework.stereotype.Component;
 * 
 * import com.fasterxml.jackson.core.JsonProcessingException; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.tyss.centralizedlogging.util.LoggingAdvice;
 * 
 * import lombok.extern.slf4j.Slf4j;
 * 
 * import java.lang.reflect.Method;
 * 
 * @Component
 * 
 * @Slf4j public class CustomThrowsAdvice implements ThrowsAdvice {
 * 
 * public void afterThrowing(Method m, Object args[], Object target, Exception
 * e) throws JsonProcessingException { ObjectMapper mapper = new ObjectMapper();
 * String methodName = m.getName(); Object[] array = m.getParameters();
 * log.error("method name : " + methodName + ", arguments : " +
 * mapper.writeValueAsString(array)); }
 */