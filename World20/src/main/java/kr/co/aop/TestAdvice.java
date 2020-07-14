package kr.co.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect //aop를 이용한 advice입니다
public class TestAdvice {
	@Around("execution(* kr.co.service.AOPTestService*.*(..))")
	public void duration(ProceedingJoinPoint pjp) throws Throwable {
		long start= System.currentTimeMillis();
		
		pjp.proceed();
		long end= System.currentTimeMillis();
		System.out.println(end-start);
		
	}
	
	
	@After("execution(* kr.co.service.AOPTestService*.*(..))")
	public void end() {
		System.out.println("*******************************");
	}
	
	//	jointpoints설정
	@Before("execution(* kr.co.service.AOPTestService*.*(..))")
	public void start(JoinPoint jp) {
		System.out.println(":::::::::::::::::::::::::::::::::");
		System.out.println(jp.getKind());
		System.out.println(jp.getSignature());
		System.out.println(jp.getTarget());
		System.out.println(":::::::::::::::::::::::::::::::::");
	}
	
	
}

