package com.phanith.loginapp.application.port.out;

import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.domain.User;

public interface UserRegisterDb {
    void save(User request);
    boolean existsByEmail(String email);
}
