package com.lsy.springcloud.controller;

import com.lsy.springcloud.pojo.Payment;
import com.lsy.springcloud.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public Result<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,Result.class);
        //参数1为请求路径，参数二为请求方法所需要的值，参数三为返回值类型
    }

    @GetMapping("/consumer/payment/get/{id}")
    public Result<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+id,Result.class);
        //参数1为请求路径，参数二为请求方法所需要的值，参数三为返回值类型
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public Result<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<Result> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/"+id,Result.class);
        if (entity.getStatusCode().is2xxSuccessful()){   //判断请求码是否成功
            return entity.getBody();
        }else {
            return new Result<>(444,"操作失败");
        }
        //当ForObject换成ForEntity,返回值对象为ResponseEntity对象，包含一些更多信息比如响应头，响应状态码，响应体等。ForObject返回值为json串
    }

}
