package com.asgarov.postprocessor;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component("receipt")
public class Receipt {

    @PostProcessable("AOP is awesome")
    public int getAmount() {
        return new Random().nextInt(100);
    }
}
