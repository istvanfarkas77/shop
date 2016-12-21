package com.shop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan
@EnableJpaRepositories
@PropertySource("classpath:db-author-config.properties")
class AuthorConfiguration {

//    @Value("${title}")
//    public String title;

    @Bean
    public String aaa() {
        return "aaa"/* + title*/;
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = (new EmbeddedDatabaseBuilder())
                .addScript("classpath:authordb/schema.sql")
                .addScript("classpath:authordb/data.sql")
                .build();

        return dataSource;
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
