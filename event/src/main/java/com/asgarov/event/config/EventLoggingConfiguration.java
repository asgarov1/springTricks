package com.asgarov.event.config;

import com.asgarov.event.annotation.Loggable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EventLoggingConfiguration {

    @Before("@annotation(loggable)")
    public void logMethodCall(JoinPoint jp, Loggable loggable) {
        getLogger(jp).info(loggable.value());
    }

    private Logger getLogger(JoinPoint jp) {
        return LoggerFactory.getLogger(jp.getTarget().getClass().getSimpleName());
    }
}
