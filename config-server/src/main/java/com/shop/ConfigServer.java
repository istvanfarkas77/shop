package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) {

        System.setProperty("spring.config.name", "configserver");

        SpringApplication.run(ConfigServer.class, args);
    }
}
