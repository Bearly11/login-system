package com.phanith.loginapp.application.port.in;

import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;

public interface UserRegister {
    void register(UserRegisterRequest request);
}
