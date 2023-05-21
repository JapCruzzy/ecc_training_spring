package com.example.ecctrainingspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class EccTrainingSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EccTrainingSpringApplication.class, args);
    }

}
