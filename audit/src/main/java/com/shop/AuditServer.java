package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AuditConfiguration.class)
@EnableJms
public class AuditServer {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "audit-server");

        SpringApplication.run(AuditServer.class, args);
    }
}
