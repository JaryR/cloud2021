package com.att.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.att.springcloud.alibaba.myHandler.CustomBlockHandler;
import com.att.springcloud.entities.CommonResult;
import com.att.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流ok",new Payment(2022L,"serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName() + "\t 服务不可用");
    }


    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流ok",new Payment(2022L,"serial002"));
    }

    @GetMapping("/rateLimit/customBlockHandler")
    @SentinelResource(value = "customBlockHandler",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handleException2")
    public CommonResult customBlockHandler(){
        return new CommonResult(200,"客户自定义异常处理",new Payment(2022L,"serial003"));
    }
}
