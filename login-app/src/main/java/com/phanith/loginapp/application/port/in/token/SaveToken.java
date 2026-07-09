package com.phanith.loginapp.application.port.in.token;

import com.phanith.loginapp.domain.enums.Type;

import java.util.UUID;

public interface SaveToken {
    void save(UUID userId, String token, Type tokenType);
}
