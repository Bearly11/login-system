package com.phanith.logintask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.phanith.logintask", "com.phanith.command"})
public class LoginTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginTaskApplication.class, args);
    }

}
