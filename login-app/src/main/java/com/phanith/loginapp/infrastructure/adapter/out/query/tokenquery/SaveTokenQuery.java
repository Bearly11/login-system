package com.phanith.loginapp.infrastructure.adapter.out.query.tokenquery;

import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.domain.Token;
import com.phanith.loginapp.infrastructure.adapter.out.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveTokenQuery implements SaveTokenDb {
    private final TokenRepository tokenRepository;
    @Override
    public void save(Token token) {

    }

    @Override
    public boolean isTokenValid(String token) {
        return tokenRepository.findByToken(token)
                .map(t-> !t.isExpired() && !t.isRevoked())
                .orElse(false);

    }
}
