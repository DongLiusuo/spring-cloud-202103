package org.example.service;

import org.example.springcloud.entities.CommonResult;
import org.example.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="cloud-provider-payment",path = "payment") //添加@FeignClient注解,指定服务提供方服务名称
public interface PaymentService {

    @PostMapping("create")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("get/{id}")
    CommonResult getById(@PathVariable("id") Long id); // 注意：@PathVariable("id") 必须这样写，不能省略括号

}
