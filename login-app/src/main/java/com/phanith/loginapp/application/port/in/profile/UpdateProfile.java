package com.phanith.loginapp.application.port.in.profile;

import com.phanith.loginapp.application.dtos.profile.UserProfileResponse;
import com.phanith.loginapp.application.dtos.profile.UserUpdateReqest;

public interface UpdateProfile {
    UserProfileResponse updateProfile(String email,UserUpdateReqest reqest);
}
