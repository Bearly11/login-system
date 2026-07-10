package com.phanith.loginapp.application.port.out;

import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.domain.User;

import java.util.Optional;

public interface UserLoginDb {
    Optional<User> findByEmail(String email);
}
