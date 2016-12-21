package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@EnableFeignClients
@Import(AuthorConfiguration.class)
public class AuthorServer {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "author-service");

        SpringApplication.run(AuthorServer.class, args);
    }
}
