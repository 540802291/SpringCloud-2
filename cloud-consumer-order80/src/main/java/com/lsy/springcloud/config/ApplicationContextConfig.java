package com.lsy.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced    //开启负载均衡
    //并未导入ribbon依然可以使用负载均衡，因为eureka-client自带ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
