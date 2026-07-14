package com.phanith.loginapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.phanith.loginapp", "com.phanith.command"})
public class LoginAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginAppApplication.class, args);
    }

}
