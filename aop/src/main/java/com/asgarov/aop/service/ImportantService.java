package com.asgarov.aop.service;

import com.asgarov.aop.annotation.Loggable;
import org.springframework.stereotype.Service;

@Service
public class ImportantService {
    @Loggable
    public void veryImportantMethod() {
        System.out.println("===I AM BEING EXECUTED===");
    }
}
