package ru.itmo.soa.util.soap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UtilSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilSoapApplication.class, args);
    }

}