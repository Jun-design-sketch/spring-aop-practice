package com.in28minutes.learnspringaop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// Configuration
@Configuration
// AOP
@Aspect
public class LoggingAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// Pointcut - When?
	// execution(* PACKAGE.*.*(..))
	// execution(* com.in28minutes.learnspringaop.aopexample.business.*.*(..))
	@Before("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()")
	public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
		// Logic - What?
		logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs());
	}
	
	@After("com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()")
	public void logMethodCallAfterExecution(JoinPoint joinPoint) {
		logger.info("After Aspect - {} is executed", joinPoint);
	}
	
	@AfterThrowing(
	pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()",
	throwing = "exception"
	)
	public void logMethodCallAfterThrowingExecution(JoinPoint joinPoint, Exception exception) {
		logger.info("AfterThrowing Exception Aspect - {} has thrown an exception {}", joinPoint, exception);
	}
	
	@AfterReturning(
	pointcut = "com.in28minutes.learnspringaop.aopexample.aspects.CommonPointcutConfig.businessPackageConfig()",
	returning = "resultValue"
	)
	public void logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, Object resultValue) {
		logger.info("AfterThrowing Aspect - {} has returned {}", joinPoint, resultValue);
	}
}
