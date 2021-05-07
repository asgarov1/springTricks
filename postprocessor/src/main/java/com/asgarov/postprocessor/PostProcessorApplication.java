package com.asgarov.postprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PostProcessorApplication {

    @Autowired
    Receipt receipt;

    public static void main(String[] args) {
        SpringApplication.run(PostProcessorApplication.class, args);
    }

    @PostConstruct
    public void sth() {
        System.out.println(receipt.getNumber());
    }
}
