package com.phanith.loginapp.application.usercase.profileservice;

import java.time.LocalDate;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.dtos.profile.UserProfileResponse;
import com.phanith.loginapp.application.dtos.profile.UserUpdateReqest;
import com.phanith.loginapp.application.mapper.UserProfileMapper;
import com.phanith.loginapp.application.port.in.profile.UpdateProfile;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.application.port.out.profile.UpdateProfileDb;
import com.phanith.loginapp.domain.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateProfileService implements UpdateProfile{
    private final UserLoginDb loginDb;
    private final UpdateProfileDb updateProfileDb;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse updateProfile(String email,UserUpdateReqest reqest){
        User user = loginDb.findByEmail(email).
        orElseThrow(()-> new NotFoundException("Not found user"));

        user.setFirstName(reqest.getFirstName());
        user.setLastName(reqest.getLastName());
        user.setGender(reqest.getGender());
        user.setUpdatedAt(LocalDate.now());
        
        updateProfileDb.updateProfile(user);
        return userProfileMapper.toProfileResponse(user);

    }
    
}
