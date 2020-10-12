package com.lsy.springcloud.controller;
import com.lsy.springcloud.pojo.Payment;
import com.lsy.springcloud.pojo.Result;
import com.lsy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public Result create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if ( result>0 ){
            return new Result(200,"插入数据成功"+serverPort,result);
        }else {
            return new Result(400,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public Result getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+ payment);
        if ( payment != null ){
            return new Result(200,"查询成功"+serverPort,payment);
        }else {
            return new Result(400,"查询失败"+id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){     //发现服务详情
        List<String> services = discoveryClient.getServices();
        for (String e : services){
            log.info("element:"+e);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId()+instance.getHost()+instance.getUri());
        }
        return this.discoveryClient;
    }

}
