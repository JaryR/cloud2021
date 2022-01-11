package com.att.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "fall back ok";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "fall back timeout";
    }
}
