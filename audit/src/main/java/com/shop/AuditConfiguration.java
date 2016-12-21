package com.shop;

import org.apache.activemq.command.ActiveMQQueue;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.jms.Destination;
import javax.sql.DataSource;

@Configuration
@ComponentScan
@EntityScan(value = "com.shop")
@EnableJpaRepositories(value = "com.shop")
@PropertySource("classpath:db-audit-config.properties")
class AuditConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = (new EmbeddedDatabaseBuilder())
                .addScript("classpath:auditdb/schema.sql")
                .build();

        return dataSource;
    }

    @Bean
    public Destination destination() {
        return new ActiveMQQueue("sample.queue");
    }

    @Bean ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
