package com.asgarov.aop;

import com.asgarov.aop.service.ImportantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AOPRunner {

    @Autowired
    private ImportantService importantService;

    @PostConstruct
    public void doImportantStuff() {
        importantService.veryImportantMethod();
    }
}
