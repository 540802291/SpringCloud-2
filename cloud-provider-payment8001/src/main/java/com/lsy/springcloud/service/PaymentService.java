package com.lsy.springcloud.service;

import com.lsy.springcloud.dao.PaymentDao;
import com.lsy.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService extends PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
