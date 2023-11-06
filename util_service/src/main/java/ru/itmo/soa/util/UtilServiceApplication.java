package ru.itmo.soa.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UtilServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilServiceApplication.class, args);
    }
}