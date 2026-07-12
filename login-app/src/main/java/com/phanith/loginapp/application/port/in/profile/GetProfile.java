package com.phanith.loginapp.application.port.in.profile;

import com.phanith.loginapp.application.dtos.profile.UserProfileResponse;

public interface GetProfile {
    UserProfileResponse profile(String email);
    
}
