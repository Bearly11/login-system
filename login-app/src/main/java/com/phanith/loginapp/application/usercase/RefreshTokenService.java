package com.phanith.loginapp.application.usercase;

import com.phanith.command.exception.UnauthorizedException;
import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.application.port.in.token.RefreshToken;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.token.RevokeTokenDb;
import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.domain.enums.Type;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefreshTokenService implements RefreshToken {
    private final UserLoginDb userLoginDb;
    private final SaveTokenDb saveTokenDb;
    private final RevokeTokenDb revokeTokenDb;
    @Override
    public AuthResponse refreshToken(String refreshToken) {
        if(refreshToken==null || refreshToken.isEmpty() || !saveTokenDb.isRefreshTokenValid(refreshToken)) {
            throw new UnauthorizedException("Invalid or expired refresh token");

        }
        String email = saveTokenDb.extractUsername(refreshToken);
        User user = userLoginDb.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("Invalid or expired refresh token"));

        revokeTokenDb.revokeAllTokens(user);
        String access = saveTokenDb.generateAccessToken(user);
        String refresh = saveTokenDb.generateRefreshToken(user);

        saveTokenDb.save(user,access, Type.ACCESS);
        saveTokenDb.save(user,refresh, Type.REFRESH);

        return new  AuthResponse(access, refresh);
    }
}
