package com.phanith.loginapp.application.port.in;

import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.application.dtos.login.UserLoginRequest;

public interface UserLogin {
    AuthResponse login(UserLoginRequest request);
}
