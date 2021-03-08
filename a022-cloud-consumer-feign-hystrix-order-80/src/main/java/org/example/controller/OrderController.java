package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hystrixOrder")
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback") //1.使用@DefaultProperties 定义全局服务降级方法
public class OrderController {

    //@Qualifier("org.example.service.PaymentService")
    @Autowired
    private PaymentService paymentService;

    @GetMapping("ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        return paymentService.ok(id);
    }

    @GetMapping("timeout1/{id}")
    @HystrixCommand(
            fallbackMethod = "timeoutHandle",
            commandProperties = {
                    @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds",value = "1500" )//1.5秒钟以内就是正常的业务逻辑
            }
    )
    public String timeout(@PathVariable Integer id) {
        return paymentService.timeout(id);
    }

    public String timeoutHandle(@PathVariable("id") Integer id) {
        return "消费端自己触发的服务降级";
    }

    @HystrixCommand ////3.使用 @HystrixCommand 使用全局服务降级方法
    @GetMapping("timeout2/{id}")
    public String testGlobalFallback(@PathVariable("id") Integer id) {
        return paymentService.timeout(id);
    }

    @GetMapping("timeout3/{id}")
    public String testNoneFallback(@PathVariable("id") Integer id) {
        return paymentService.timeout(id);
    }

    //2.定义全局服务降级方法(不能有参数)
    public String globalFallback() {
        return "消费端的全局降级方法";
    }
}
