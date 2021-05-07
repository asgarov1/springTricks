package com.asgarov.event.listener;

import com.asgarov.event.PaymentReceivedEvent;
import com.asgarov.event.annotation.Loggable;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements ApplicationListener<PaymentReceivedEvent> {

    @Loggable("Event processed!!!")
    @Override
    public void onApplicationEvent(PaymentReceivedEvent event) {
        // do sth with the event
    }
}


