package com.phanith.loginapp.infrastructure.adapter.out.query.tokenquery;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.mapper.UserMapper;
import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.domain.Token;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.domain.enums.Type;
import com.phanith.loginapp.infrastructure.adapter.out.database.Tokens;
import com.phanith.loginapp.infrastructure.adapter.out.database.Users;
import com.phanith.loginapp.infrastructure.adapter.out.repository.TokenRepository;
import com.phanith.loginapp.infrastructure.adapter.out.repository.UserRepository;
import com.phanith.loginapp.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SaveTokenQuery implements SaveTokenDb {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void save(User user, String token, Type tokenType) {
        var newToken = new Tokens();
        newToken.setToken(token);
        newToken.setTokenType(tokenType);
        newToken.setExpired(false);
        newToken.setRevoked(false);
        Users exist = userRepository.findById(user.getId())
                        .orElseThrow(()->
                                new NotFoundException("User not found"));
        newToken.setUser(exist);
        tokenRepository.save(newToken);

    }

    @Override
    public boolean isTokenValid(String token) {
        return tokenRepository.findByToken(token)
                .map(t-> !t.isExpired() && !t.isRevoked())
                .orElse(false);

    }

    @Override
    public String generateAccessToken(User user) {
        UserDetails userDetails = (UserDetails) authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return jwtService.generateAccessToken(userDetails);
    }

    @Override
    public String generateRefreshToken(User user) {
        UserDetails userDetails = (UserDetails) authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return jwtService.generateRefreshToken(userDetails);
    }
}
