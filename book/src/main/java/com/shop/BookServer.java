package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@Import(BookConfiguration.class)
public class BookServer {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "book-server");

        //System.setProperty("server.port", args[0]);

        SpringApplication.run(BookServer.class, args);
    }
}
