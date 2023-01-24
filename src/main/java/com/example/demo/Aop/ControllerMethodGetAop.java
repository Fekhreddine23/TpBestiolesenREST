package com.example.demo.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Aspect
@Component
public class ControllerMethodGetAop {
	

	//Cibler toutes les méthodes de nos packages qui commencent par get
	@Before("execution(* com.example.demo..get*(..)) ")
	public void CiblerGet(JoinPoint joinPoint) {
		
		System.out.println("Cible les methodes Get du package " +joinPoint.getSignature());
	}

}


