package org.zerock.aop;


import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		//실행되는 함수 이름을 가져오고 출력
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("@Before : " + method.getName() + " 메서드 실행");
	}
	
	//doAdd함수가 실행될 때만 실행되는 함수 (표현식 이용)
	 @Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		// log.info("doAdd @Before : 메소드 실행하기 이전");
		System.out.println("doAdd @Before : 메소드 실행하기 이전");

	}
	
		@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
		public void logException(Exception exception) {
			log.info("Exception....!!!");
			log.info("exception : " + exception);
		}

	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		System.out.println("====================================================");
		long start = System.currentTimeMillis();
		
		//log.info("@Around : before + after, 예외가 발생하더라도 실행되는 부분");
		log.info("Target : " + pjp.getTarget());
		log.info("파라미터 : " + Arrays.toString(pjp.getArgs()));
		
		// invoke method
		Object result = null;
		
		try {
			result = pjp.proceed();
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("함수 실행 시간 : " + (end - start));
		System.out.println("====================================================");
		return result;
	}
	
	//리턴 객체가 있는 경우
	@AfterReturning(value = "execution(* org.zerock.service.SampleService*.*(..))", returning = "returnValue")
	public void logAfter(JoinPoint joinPoint, Object returnValue) {
		 MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
	        Method method = methodSignature.getMethod();
		System.out.println("@AfterReturning : " + method.getName() + "메소드가 성공적으로 실행된 후 리턴 완료: " + returnValue);
	}
	
}