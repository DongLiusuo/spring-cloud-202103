package org.example.service;

import org.example.springcloud.entities.CommonResult;
import org.example.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("cloud-provider-payment") //添加@FeignClient注解,指定服务提供方服务名称
@RequestMapping("payment") // 部分版本可能不支持这样写 （不支持在@FeignClient上的接口使用@RequestMapping）
public interface PaymentService {

    @PostMapping("create")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("get/{id}")
    CommonResult getById(@PathVariable("id") Long id); // 注意：@PathVariable("id") 必须这样写，不能省略括号

}
