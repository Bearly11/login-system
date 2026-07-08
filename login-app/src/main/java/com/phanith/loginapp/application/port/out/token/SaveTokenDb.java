package com.phanith.loginapp.application.port.out.token;

import com.phanith.loginapp.domain.Token;

public interface SaveTokenDb {
    void save(Token token);
    boolean isTokenValid(String token);
}
