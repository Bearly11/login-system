package com.phanith.loginapp.infrastructure.adapter.out.query.profile;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.port.out.profile.DeleteAccountDb;
import com.phanith.loginapp.infrastructure.adapter.out.repository.TokenRepository;
import com.phanith.loginapp.infrastructure.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteProfileQuery implements DeleteAccountDb {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public void delete(String email) {
        var users = userRepository.findByEmail(email)
                .orElseThrow(()->
                        new NotFoundException("Not found user"));
        tokenRepository.deleteByUserId(users.getId());
        userRepository.deleteById(users.getId());
    }
}
