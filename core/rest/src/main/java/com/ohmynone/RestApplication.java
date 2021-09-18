package com.ohmynone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ohmynone.rest", "com.ohmynone.user"})
public class RestApplication {
    public static void main(String[] args) {
SpringApplication.run(RestApplication.class, args);
    }
}
