package com.phanith.loginapp.application.dtos.login;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
