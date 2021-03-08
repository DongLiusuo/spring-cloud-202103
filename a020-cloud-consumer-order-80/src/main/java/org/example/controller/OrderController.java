package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.entities.CommonResult;
import org.example.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    // private final static String PAYMENT_URL = "http://localhost:8001/";
    // 修改为服务名进行访问
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT/";


    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        log.info("消费者调用创建接口...");
        return restTemplate.postForObject(PAYMENT_URL + "payment/create", payment, CommonResult.class);
    }

    @GetMapping("get/{id}")
    public CommonResult getById(@PathVariable Long id) {
        log.info("消费者调用创建接口...");
        return restTemplate.getForObject(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
    }

}
