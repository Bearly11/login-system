package com.phanith.loginapp.infrastructure.adapter.in;

import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.application.port.in.UserRegister;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserRegister userRegister;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest dto){
        userRegister.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
