package com.phanith.loginapp.application.usercase;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.port.in.UserLogout;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.token.RevokeTokenDb;
import com.phanith.loginapp.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutService implements UserLogout {
    private final UserLoginDb userLoginDb;
    private final RevokeTokenDb revokeTokenDb;
    @Override
    public void logout(String email) {
        var user = userLoginDb.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
        revokeTokenDb.revokeToken(user.getEmail());
    }
}
