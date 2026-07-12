package com.phanith.loginapp.application.port.in.profile;

import com.phanith.loginapp.application.dtos.profile.ChangePasswordRequest;

public interface ChangePassword {
    void changePassword(String email,ChangePasswordRequest request);
    
}
