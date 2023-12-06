package com.example.bands_service_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BandsService3Application {

    public static void main(String[] args) {
        SpringApplication.run(BandsService3Application.class, args);
    }

}
