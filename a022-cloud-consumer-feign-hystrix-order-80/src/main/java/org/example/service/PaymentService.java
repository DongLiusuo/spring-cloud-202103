package org.example.service;

import org.example.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cloud-provider-hystrix-payment",
        path = "hystrixPayment",
        configuration = FeignConfiguration.class
        ,fallback = PaymentService.PaymentServiceFeignFallbackImpl.class
)
public interface PaymentService {


    /**
     * 正常的方法
     * @param id 入参
     * @return info
     */
    @GetMapping("ok/{id}")
    String ok(@PathVariable("id") Integer id);

    /**
     * 模拟超时的方法
     * @param id 入参
     * @return 返回值
     */
    @GetMapping("timeout/{id}")
    String timeout(@PathVariable("id") Integer id);


    @Component
    class PaymentServiceFeignFallbackImpl implements PaymentService {

        @Override
        public String ok(Integer id) {
            return "使用FeignClient实现类降级的方法执行了";
        }

        @Override
        public String timeout(Integer id) {
            return "使用FeignClient实现类降级的方法执行了";
        }

    }
}
