package com.att.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBlancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
