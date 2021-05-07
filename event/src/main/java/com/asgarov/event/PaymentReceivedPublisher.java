package com.asgarov.event;

import com.asgarov.event.annotation.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PaymentReceivedPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Loggable("Payment Received!!!")
    public void publishEvent(String message) {
        var event = new PaymentReceivedEvent(this, message);
        publisher.publishEvent(event);
    }
}
