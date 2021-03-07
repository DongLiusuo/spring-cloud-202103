package org.example.controller;

import lombok.extern.slf4j.Slf4j;

import org.example.springcloud.entities.CommonResult;
import org.example.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("payment")
public class PatmentController {

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        log.info("调用创建接口...");
        return new CommonResult(200, "调用创建接口成功，假设创建了一条记录", 1);
    }

    @GetMapping("get/{id}")
    public CommonResult getById(@PathVariable Long id) {
        log.info("调用查询接口");
        return new CommonResult(200, "调用查询接口成功,id：" + id);
    }

}
