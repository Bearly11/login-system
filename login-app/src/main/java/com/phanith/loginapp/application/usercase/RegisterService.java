package com.phanith.loginapp.application.usercase;

import com.phanith.command.exception.DuplicateException;
import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.application.mapper.UserMapper;
import com.phanith.loginapp.application.port.in.UserRegister;
import com.phanith.loginapp.application.port.out.UserRegisterDb;
import com.phanith.loginapp.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication(scanBasePackages = "com.phanith.command.exception")
@RequiredArgsConstructor
public class RegisterService implements UserRegister {
    private final UserRegisterDb userRegisterDb;
    private final UserMapper userMapper;
    @Override
   public void register(UserRegisterRequest request) {
        if(userRegisterDb.existsByEmail(request.getEmail())) {
            throw new DuplicateException("Email already exists");
        }
        UUID uuid = UUID.randomUUID();
        User user = userMapper.toEntity(request);
        user.setId(uuid);
        user.setCreatedAt(LocalDate.now());
        userRegisterDb.save(user);
    }
}
