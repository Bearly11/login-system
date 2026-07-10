package com.phanith.loginapp.infrastructure.adapter.out.query;

import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.infrastructure.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginQuery implements UserLoginDb {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(entity -> new User(
                        entity.getId(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getGender(),
                        entity.getEmail(),
                        entity.getPassword(),
                        entity.getCreateAt(),
                        entity.getUpdateAt()
                ));
    }
}
