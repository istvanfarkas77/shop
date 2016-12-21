package com.shop.web.lib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@ComponentScan
@EnableKafka
public class WebLibServer {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebLibService webBookService() {
        return new WebLibService();
    }

    @Bean
    public WebLibController webLibController() {
        return new WebLibController();
    }

    @Bean
    public WebLibHomeController webLibHomeController() {
        return new WebLibHomeController();
    }

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "web-lib-server");

        SpringApplication.run(WebLibServer.class, args);
    }
}
