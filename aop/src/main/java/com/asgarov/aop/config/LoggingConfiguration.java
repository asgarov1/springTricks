package com.asgarov.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingConfiguration {

    @Pointcut("@annotation(com.asgarov.aop.annotation.Loggable)")
    public void anyLoggableMethod() {
    }

    @Before("anyLoggableMethod()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().toShortString();
        getLogger(jp).info("BEFORE: " + methodName);
    }

    @AfterReturning("anyLoggableMethod()")
    public void logMethodReturn(JoinPoint jp) {
        String methodName = jp.getSignature().toShortString();
        getLogger(jp).info("AFTER: " + methodName);
    }

    private Logger getLogger(JoinPoint jp) {
        return LoggerFactory.getLogger(jp.getTarget().getClass().getSimpleName());
    }
}
