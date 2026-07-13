package com.phanith.loginapp.application.usercase.profileservice;


import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.port.in.profile.DeleteAccount;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.profile.DeleteAccountDb;

import com.phanith.loginapp.application.port.out.token.RevokeTokenDb;
import com.phanith.loginapp.domain.User;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DeleteProfileService implements DeleteAccount{
    private final DeleteAccountDb deleteAccountDb;
    private final UserLoginDb userLoginDb;

    @Override
    public void deleteProfile(String email){
        User user = userLoginDb.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User not found"));
        deleteAccountDb.delete(user.getEmail());

    }
    
}
