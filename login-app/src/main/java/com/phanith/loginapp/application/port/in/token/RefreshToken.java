package com.phanith.loginapp.application.port.in.token;

import com.phanith.loginapp.application.dtos.login.AuthResponse;

public interface RefreshToken {
    AuthResponse refreshToken(String refreshToken);
}
