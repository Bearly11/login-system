package com.phanith.loginapp.infrastructure.adapter.out.query.tokenquery;

import com.phanith.loginapp.application.port.out.token.RevokeTokenDb;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.infrastructure.adapter.out.database.Tokens;
import com.phanith.loginapp.infrastructure.adapter.out.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RevokeTokenQuery implements RevokeTokenDb {
    private final TokenRepository tokenRepository;
    @Override
    public void revokeAllTokens(User user) {
        List<Tokens> validToken = tokenRepository.
                findAllByUserIdAndExpiredFalseAndRevokedFalse(user.getId());
        if (validToken.isEmpty()) {
            return;
        }
        for (Tokens token : validToken) {
            token.setExpired(true);
            token.setRevoked(true);

        }
        tokenRepository.saveAll(validToken);

    }

    @Override
    public void revokeToken(String token) {
        var storedToken = tokenRepository.findByToken(token)
                .orElseThrow(()->
                        new RuntimeException("token not found"));
        storedToken.setExpired(true);
        storedToken.setRevoked(true);
        tokenRepository.save(storedToken);

    }
}
