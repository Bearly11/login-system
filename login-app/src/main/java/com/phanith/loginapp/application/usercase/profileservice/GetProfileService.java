package com.phanith.loginapp.application.usercase.profileservice;

import com.phanith.command.exception.NotFoundException;
import com.phanith.loginapp.application.dtos.profile.UserProfileResponse;
import com.phanith.loginapp.application.mapper.UserProfileMapper;
import com.phanith.loginapp.application.port.in.profile.GetProfile;
import com.phanith.loginapp.application.port.out.UserLoginDb;
import com.phanith.loginapp.domain.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetProfileService implements GetProfile{
    private final UserLoginDb loginDb;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse profile(String email){
        User user = loginDb.findByEmail(email)
        .orElseThrow(()-> new NotFoundException("User not found"));

        return userProfileMapper.toProfileResponse(user);
    }
    
}
