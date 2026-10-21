package com.msa.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.msa.common", "com.msa.order"})
@EnableJpaRepositories(basePackages = {"com.msa.order.repository", "com.msa.common.repository"})
@EntityScan(basePackages = {"com.msa.**.entity"})
@EnableRedisRepositories(basePackages = "com.msa.common.repository")
@EnableEurekaClient
public class OrderApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class, args);
  }
}