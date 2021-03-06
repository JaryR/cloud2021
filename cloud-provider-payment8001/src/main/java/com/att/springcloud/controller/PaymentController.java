package com.att.springcloud.controller;

import com.att.springcloud.entities.CommonResult;
import com.att.springcloud.entities.Payment;
import com.att.springcloud.service.PaymentService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("insert result:" + result);
        if(result > 0){
            return new CommonResult(200,"insert ok,serverPort:" + serverPort,result);
        }
        return new CommonResult(400,"insert fail",null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("select result:" + payment);
        if(payment != null){
            return new CommonResult(200,"select ok,serverPort:" + serverPort,payment);
        }
        return new CommonResult(400,"select fail",null);
    }

    @GetMapping(value = "payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for ( String element : services) {
            log.info("***element:" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin(){
        return "zipkin server fall back!";
    }
}
