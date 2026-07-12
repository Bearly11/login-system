package com.phanith.loginapp.application.usercase.profileservice;


import com.phanith.loginapp.application.port.in.profile.DeleteAccount;
import com.phanith.loginapp.application.port.out.profile.DeleteAccountDb;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DeleteProfileService implements DeleteAccount{
    private DeleteAccountDb deleteAccountDb;

    @Override
    public void deleteProfile(String email){
        deleteAccountDb.delete(email);
    }
    
}
