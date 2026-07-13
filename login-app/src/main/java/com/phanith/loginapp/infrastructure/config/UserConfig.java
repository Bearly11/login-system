package com.phanith.loginapp.infrastructure.config;

import com.phanith.loginapp.application.mapper.UserMapper;
import com.phanith.loginapp.application.mapper.UserProfileMapper;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.UserRegisterDb;
import com.phanith.loginapp.application.port.out.profile.ChangePasswordDb;
import com.phanith.loginapp.application.port.out.profile.DeleteAccountDb;
import com.phanith.loginapp.application.port.out.profile.UpdateProfileDb;
import com.phanith.loginapp.application.port.out.token.RevokeTokenDb;
import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.application.usercase.LoginService;
import com.phanith.loginapp.application.usercase.RegisterService;
import com.phanith.loginapp.application.usercase.profileservice.ChangePasswordService;
import com.phanith.loginapp.application.usercase.profileservice.DeleteProfileService;
import com.phanith.loginapp.application.usercase.profileservice.GetProfileService;
import com.phanith.loginapp.application.usercase.profileservice.UpdateProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public RegisterService registerService(UserRegisterDb userRegisterDb,UserMapper userMapper) {
        return new RegisterService(userRegisterDb,userMapper);
    }

    @Bean
    public LoginService loginService(UserLoginDb userLoginDb, SaveTokenDb saveTokenDb, RevokeTokenDb revokeTokenDb,
        PasswordEncoder passwordEncoder
    ) {
        return new LoginService(userLoginDb,saveTokenDb,revokeTokenDb,passwordEncoder);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GetProfileService getProfileService(UserLoginDb userLoginDb, UserProfileMapper userMapper) {
        return new GetProfileService(userLoginDb,userMapper);
    }

    @Bean
    public UpdateProfileService updateProfileService(UserLoginDb userLoginDb, UpdateProfileDb updateProfileDb, UserProfileMapper userMapper) {
        return new UpdateProfileService(userLoginDb,updateProfileDb,userMapper);
    }
    @Bean
    public ChangePasswordService changePasswordService(UserLoginDb userLoginDb, ChangePasswordDb changePasswordDb, PasswordEncoder passwordEncoder) {
        return new ChangePasswordService(userLoginDb,changePasswordDb,passwordEncoder);
    }
    @Bean
    public DeleteProfileService deleteProfileService(UserLoginDb userLoginDb, DeleteAccountDb deleteAccountDb) {
        return new DeleteProfileService(deleteAccountDb,userLoginDb);
    }
}
