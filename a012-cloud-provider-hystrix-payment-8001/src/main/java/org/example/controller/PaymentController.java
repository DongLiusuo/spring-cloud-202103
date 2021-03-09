package org.example.controller;

import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hystrixPayment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    /**
     * 正常的方法
     * @param id 入参
     * @return info
     */
    @GetMapping("ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        return paymentService.ok(id);
    }

    /**
     * 模拟超时的方法
     * @param id 入参
     * @return 返回值
     */
    @GetMapping("timeout/{id}")
    public String timeout(@PathVariable("id") Integer id) {
        return paymentService.timeout(id);
    }



    @GetMapping("paymentCircuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }
}
