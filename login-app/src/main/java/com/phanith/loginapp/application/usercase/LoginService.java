package com.phanith.loginapp.application.usercase;

import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.application.dtos.login.UserLoginRequest;
import com.phanith.loginapp.application.port.in.UserLogin;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.token.RevokeTokenDb;
import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.domain.enums.Type;
import com.phanith.loginapp.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RequiredArgsConstructor
public class LoginService implements UserLogin {
    private final UserLoginDb userLoginDb;
    private final SaveTokenDb saveTokenDb;
    private final RevokeTokenDb revokeTokenDb;

    @Override
    public AuthResponse login(UserLoginRequest request) {
        User user = userLoginDb.login(request.getEmail(),request.getPassword());
        if(!user.getEmail().equals(request.getEmail())){
            throw new UsernameNotFoundException("Invalid email or password");
        }
        if(!user.getPassword().equals(request.getPassword())){
            throw new UsernameNotFoundException("Invalid email or password");
        }
        revokeTokenDb.revokeAllTokens(user);
        String access = saveTokenDb.generateAccessToken(user);
        String refresh = saveTokenDb.generateRefreshToken(user);

        saveTokenDb.save(user,access, Type.ACCESS);
        saveTokenDb.save(user,refresh, Type.REFRESH);


        return new AuthResponse(access,refresh);

    }
}
