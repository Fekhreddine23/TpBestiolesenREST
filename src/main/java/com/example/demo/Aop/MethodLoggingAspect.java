package com.example.demo.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/*
 * 2. Aspect «logger de nom de méthode»•Ajouter un Aspect qui va logger le nom de la méthode dans laquelle on rentre.
 * •Cet aspect devra cibler toutes les méthodes publiques des controllers.
 * •Utiliserle paramètre de méthode JoinPoint sur l’advice et joinPoint.getSignature().getName()
 * pour connaître le nom de la méthode en cours.
 * 
 * 
 * 
 * */
@Aspect
@Component
public class MethodLoggingAspect {
	
	 @Before("execution(* com.example.demo.controllers..*(..)))\")")
    public void logMethodEntryController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Cet aspect cibler toutes les méthodes publiques des controllers : " + methodName);
    }

}
