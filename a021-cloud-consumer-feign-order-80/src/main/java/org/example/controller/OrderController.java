package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.PaymentService;
import org.example.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feignOrder")
@Slf4j
public class OrderController {



    @Autowired
    private PaymentService paymentService;

    @GetMapping("get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        log.info("通过openfeign调用");
        return paymentService.getById(id);
    }

}
