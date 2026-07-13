package com.phanith.loginapp.infrastructure.adapter.out.query.profile;


import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.port.out.profile.ChangePasswordDb;
import com.phanith.loginapp.infrastructure.adapter.out.database.Users;
import com.phanith.loginapp.infrastructure.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChangePasswordQuery implements ChangePasswordDb {
    private final UserRepository userRepository;

    @Override
    public void changePassword(String email, String newEncoderPassword) {
        Users user = userRepository.findByEmail(email).orElseThrow(()->
                new NotFoundException("Not found user"));
        user.setPassword(newEncoderPassword);
        userRepository.save(user);
    }
}
