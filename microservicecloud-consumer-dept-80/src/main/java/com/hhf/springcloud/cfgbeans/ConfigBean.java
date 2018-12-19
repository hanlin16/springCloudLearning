package com.hhf.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    /**
     * RestTemplate提供了多种便捷访问远程Http服务的方法，
     * 是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集。
     */
    @Bean
    @LoadBalanced //要求客户端通过Rest去访问微服务的时候自带负载均衡
    public RestTemplate getRestTemplate(){
            return new RestTemplate();
    }

    @Bean
    public IRule myRule(){
        //用重新选择的随机算法替代默认的轮询算法。
        //return new RandomRule();
        return new RetryRule();
    }


}
