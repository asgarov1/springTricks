package com.asgarov.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope
public class EventRunner {

    @Autowired
    private PaymentReceivedPublisher publisher;

    @PostConstruct
    public void run() {
        publisher.publishEvent("Received payment from ..");
    }
}
