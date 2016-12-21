package com.shop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import javax.sql.DataSource;

@Configuration
@ComponentScan
@EntityScan
@EnableJpaRepositories
@PropertySource("classpath:db-book-config.properties")
class BookConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = (new EmbeddedDatabaseBuilder())
                .addScript("classpath:bookdb/schema.sql")
                .addScript("classpath:bookdb/data.sql")
                .build();

        return dataSource;
    }

    @Bean
    public CacheManager cacheCacheManager() {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }

    @Bean EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setShared(true);

        return factory;
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
