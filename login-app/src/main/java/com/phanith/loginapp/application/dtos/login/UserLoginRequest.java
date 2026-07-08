package com.phanith.loginapp.application.dtos.login;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginRequest {
    private String email;
    private String password;
}
