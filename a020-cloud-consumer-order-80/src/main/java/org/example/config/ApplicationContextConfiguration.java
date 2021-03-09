package org.example.config;

import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
Ribbon默认的负载均衡策略
RandomRule	随机策略	随机选择server
RoundRobinRule	轮询策略	按照顺序选择server（ribbon默认策略）
RetryRule	重试策略	在一个配置时间段内，当选择server不成功，则一直尝试选择一个可用的server
BestAvailableRule	最低并发策略	逐个考察server，如果server断路器打开，则忽略，再选择其中并发链接最低的server
AvailabilityFilteringRule	可用过滤策略	过滤掉一直失败并被标记为circuit tripped的server，过滤掉那些高并发链接的server（active connections超过配置的阈值）
ResponseTimeWeightedRule	响应时间加权重策略	根据server的响应时间分配权重，响应时间越长，权重越低，被选择到的概率也就越低。响应时间越短，权重越高，被选中的概率越高，这个策略很贴切，综合了各种因素，比如：网络，磁盘，io等，都直接影响响应时间
ZoneAvoidanceRule	区域权重策略	综合判断server所在区域的性能，和server的可用性，轮询选择server并且判断一个AWS Zone的运行性能是否可用，剔除不可用的Zone中的所有server

springcloud 2020.0.x及以后版本，去掉了除eureka之外的所有netflex组件
新版本废弃了ribbin
https://blog.csdn.net/qq_35799668/article/details/114534023
*/

@Configuration
@EnableEurekaClient // 说明这是一个euraka的客户端
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = RoundRobinRule.class) // 负载均衡策略配置
public class ApplicationContextConfiguration {


    @Bean
    @LoadBalanced // 开启负载均衡，默认为轮询
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
