package com.phanith.loginapp.application.usercase;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.dtos.login.AuthResponse;
import com.phanith.loginapp.application.dtos.login.UserLoginRequest;
import com.phanith.loginapp.application.port.in.UserLogin;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.token.RevokeTokenDb;
import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.domain.User;
import com.phanith.loginapp.domain.enums.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class LoginService implements UserLogin {
    private final UserLoginDb userLoginDb;
    private final SaveTokenDb saveTokenDb;
    private final RevokeTokenDb revokeTokenDb;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(UserLoginRequest request) {
        User user = userLoginDb.findByEmail(request.getEmail())
                .orElseThrow(()->
                        new NotFoundException("User not found with email: " + request.getEmail()));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
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
