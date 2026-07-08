package com.phanith.loginapp.infrastructure.config;

import com.phanith.loginapp.application.mapper.UserMapper;
import com.phanith.loginapp.application.port.out.UserRegisterDb;
import com.phanith.loginapp.application.usercase.RegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public RegisterService registerService(UserRegisterDb userRegisterDb,UserMapper userMapper) {
        return new RegisterService(userRegisterDb,userMapper);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
