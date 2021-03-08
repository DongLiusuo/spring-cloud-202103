package org.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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


}
