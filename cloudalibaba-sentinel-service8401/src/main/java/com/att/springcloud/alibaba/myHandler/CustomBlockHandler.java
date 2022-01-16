package com.att.springcloud.alibaba.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.att.springcloud.entities.CommonResult;

public class CustomBlockHandler {
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(444,"客户自定义全局异常处理001");
    }
    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(444,"客户自定义全局异常处理002");
    }
}
