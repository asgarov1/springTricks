package com.asgarov.event;

import org.springframework.context.ApplicationEvent;

public class PaymentReceivedEvent extends ApplicationEvent {

    private final String message;

    public PaymentReceivedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
