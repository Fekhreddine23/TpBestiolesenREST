package com.example.demo.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogExceptionAop {
	
	
	@AfterThrowing(pointcut = "execution(* com.example.demo.services.PersonService.update(..))",throwing = "e")
	public void logException(JoinPoint joinPoint, Throwable e) {
        System.out.println("Une exception  rencontré dans la methode " + joinPoint.getSignature().getName() + 
        		" avec ce message: " + e.getMessage());
    }

}
