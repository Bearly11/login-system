package com.phanith.loginapp.application.usercase.profileservice;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.dtos.profile.ChangePasswordRequest;
import com.phanith.loginapp.application.port.in.profile.ChangePassword;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.profile.ChangePasswordDb;
import com.phanith.loginapp.domain.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangePasswordService implements ChangePassword{
    private final UserLoginDb userLoginDb;
    private final ChangePasswordDb changePasswordDb;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(String email,ChangePasswordRequest request){
        User user = userLoginDb.findByEmail(email).
        orElseThrow(()-> new NotFoundException("Not found user"));
        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            throw new NotFoundException("Old password is incorrect ");
        }
        String newPass = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(newPass);
        changePasswordDb.changePassword(email, newPass);

    }
    
}
