package com.phanith.loginapp.infrastructure.adapter.in;

import com.phanith.command.exception.UnauthorizedException;
import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.application.dtos.login.UserLoginRequest;
import com.phanith.loginapp.application.dtos.login.UserRegisterRequest;
import com.phanith.loginapp.application.port.in.UserLogin;
import com.phanith.loginapp.application.port.in.UserLogout;
import com.phanith.loginapp.application.port.in.UserRegister;
import com.phanith.loginapp.application.port.in.token.RefreshToken;
import com.phanith.loginapp.security.CookieUtil;
import com.phanith.loginapp.security.SecurityUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private final UserRegister userRegister;
    private final UserLogin userLogin;
    private final CookieUtil cookieUtil;
    private final UserLogout userLogout;
    private final RefreshToken refreshToken;
    private final SecurityUtil securityUtil;

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest dto){
        userRegister.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequest dto, HttpServletResponse response){
        AuthResponse tokens = userLogin.login(dto);
        cookieUtil.addAccessTokenCookie(response,tokens.getAccessToken());
        cookieUtil.addRefreshTokenCookie(response,tokens.getRefreshToken());
        return ResponseEntity.ok().build();
    }
    @PostMapping("refresh")
    public ResponseEntity<Void> refresh(HttpServletRequest request, HttpServletResponse response){
        String refreshTokens = extractCookie(request, CookieUtil.REFRESH_COOKIE)
                .orElseThrow(()-> new UnauthorizedException("Missing Refresh Token"));

        AuthResponse tokens = refreshToken.refreshToken(refreshTokens);
        cookieUtil.addAccessTokenCookie(response, tokens.getAccessToken());
        cookieUtil.addRefreshTokenCookie(response, tokens.getRefreshToken());
        return ResponseEntity.ok().build();
    }
    @PostMapping("logout")
    public ResponseEntity<Void> logout(HttpServletResponse response){
        userLogout.logout(securityUtil.getCurrentUserEmail());
        cookieUtil.clearAuthCookies(response);
        return ResponseEntity.ok().build();
    }

    private java.util.Optional<String> extractCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies ==  null) return Optional.empty();
        for(Cookie cookie : cookies){
            if(name.equals(cookie.getName()) && !cookie.getValue().isBlank()){
                return java.util.Optional.of(cookie.getValue());
            }
        }
        return Optional.empty();
    }
}
