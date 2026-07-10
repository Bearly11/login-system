package com.phanith.loginapp.infrastructure.adapter.out.query;

import com.phanith.loginapp.application.port.in.UserRegister;
import com.phanith.loginapp.application.port.out.UserRegisterDb;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.infrastructure.adapter.out.database.Users;
import com.phanith.loginapp.infrastructure.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class RegisterQuery implements UserRegisterDb {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(User request) {
        var user = new Users();
        user.setId(request.getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreateAt(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return  userRepository.existsByEmail(email);
    }
}
