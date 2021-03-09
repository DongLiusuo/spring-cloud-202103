package org.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常的方法
     * @param id 入参
     * @return info
     */
    public String ok(Integer id) {
        return "线程：" + Thread.currentThread().getName() + "\tok方法，方法入参：" + id;
    }

    /**
     * 模拟超时的方法
     * @param id 入参
     * @return 返回值
     */
    @HystrixCommand(
            fallbackMethod = "timeoutHandler", // 降级的方法
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000") //5秒钟以内就执行正常的业务逻辑,反之执行降级方法
            }
    )
    public String timeout(Integer id) {
        int second = 6;
        try { TimeUnit.SECONDS.sleep(second); } catch (InterruptedException e) { e.printStackTrace(); }
        return "8001线程：" + Thread.currentThread().getName() + "\ttimeout方法，方法入参：" + id;
    }

    /**
     * 服务降级后的处理方法
     * 方法签名（参数+返回值）需要和原方法一致，方法名不同即可
     * @param id 入参
     * @return 返回值
     */
    public String timeoutHandler(Integer id) {
        return "8001线程：" + Thread.currentThread().getName() + "\ttimeout方法的降级方法，方法入参：" + id;
    }

    /**
     *
     * @HystrixProperty 的参数在HystrixCommandProperties.java中可以看到
     * 下面配置的意思：
     *  在10s内的10次请求失败率60%就断路
     *  随着请求正确率的提升，服务也会逐渐恢复
     * @param id
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间范围
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少后断路，此处为60%
            }
    )
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) throw new IllegalArgumentException("id不能为负数");
        String serialNumber = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t成功,流水号：" + serialNumber;
    }

    /**
     * 降级的方法
     * @param id
     * @return
     */
    public String paymentCircuitBreakerFallback(Integer id) {
        return "执行了降级的方法" + id;
    }

}
