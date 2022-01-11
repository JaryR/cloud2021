package com.att.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
    String paymentInfo_ok(Integer id);
    String paymentInfo_timeout(Integer id);
    String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
