package com.phanith.loginapp.application.usercase;

import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.application.dtos.login.UserLoginRequest;
import com.phanith.loginapp.application.port.in.UserLogin;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.domain.User;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class LoginService implements UserLogin {
    private final UserLoginDb userLoginDb;
    @Override
    public AuthResponse login(UserLoginRequest request) {
        User doUser = new User();
        doUser.setEmail(request.getEmail());

        User user = userLoginDb.login(doUser);
        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }
        if(!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or Password");
        }
        return null;

    }
}
