package com.phanith.loginapp.application.port.out;

import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.domain.User;

public interface UserLoginDb {
    User login (User user);
}
