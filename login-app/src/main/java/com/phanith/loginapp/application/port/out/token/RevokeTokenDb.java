package com.phanith.loginapp.application.port.out.token;

import com.phanith.loginapp.domain.Token;
import com.phanith.loginapp.domain.User;

public interface RevokeTokenDb {
    void revokeAllTokens(User user);
    void revokeToken(String token);

}
