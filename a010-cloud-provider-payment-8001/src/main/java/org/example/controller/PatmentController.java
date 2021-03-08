package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.entities.CommonResult;
import org.example.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("payment")
public class PatmentController {

    @Value("${server.port}")
    private String port;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        log.info("调用创建接口...");
        return new CommonResult(200, "调用创建接口成功，假设创建了一条记录，我的端口号是："+port, 1);
    }

    @GetMapping("get/{id}")
    public CommonResult getById(@PathVariable Long id) {
        log.info("调用查询接口");
        return new CommonResult(200, "调用查询接口成功"+"，我的端口号是："+port+",id：" + id);
    }

}
