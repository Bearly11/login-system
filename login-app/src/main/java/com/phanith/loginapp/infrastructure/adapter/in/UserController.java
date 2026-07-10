package com.phanith.loginapp.infrastructure.adapter.in;

import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.application.dtos.login.UserLoginRequest;
import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.application.port.in.UserLogin;
import com.phanith.loginapp.application.port.in.UserRegister;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private final UserRegister userRegister;
    private final UserLogin userLogin;

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest dto){
        userRegister.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginRequest dto){
        return ResponseEntity.ok(userLogin.login(dto));
    }
}
