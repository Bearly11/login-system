package com.phanith.loginapp.application.port.out.token;

import com.phanith.loginapp.domain.Token;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.domain.enums.Type;

import java.util.UUID;

public interface SaveTokenDb {
    void save(User user, String token, Type tokenType);
    boolean isTokenValid(String token);
    String generateAccessToken(User user);
    String generateRefreshToken(User user);

}
