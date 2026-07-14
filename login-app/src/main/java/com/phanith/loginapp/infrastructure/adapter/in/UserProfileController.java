package com.phanith.loginapp.infrastructure.adapter.in;

import com.phanith.loginapp.application.dtos.profile.ChangePasswordRequest;
import com.phanith.loginapp.application.dtos.profile.UserProfileResponse;
import com.phanith.loginapp.application.dtos.profile.UserUpdateReqest;
import com.phanith.loginapp.application.port.in.profile.ChangePassword;
import com.phanith.loginapp.application.port.in.profile.DeleteAccount;
import com.phanith.loginapp.application.port.in.profile.GetProfile;
import com.phanith.loginapp.application.port.in.profile.UpdateProfile;
import com.phanith.loginapp.security.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile/")
@RequiredArgsConstructor
public class UserProfileController {
    private final GetProfile getProfile;
    private final UpdateProfile updateProfile;
    private final ChangePassword changePassword;
    private final DeleteAccount deleteAccount;
    private final SecurityUtil securityUtil;


    @GetMapping("profile")
    public ResponseEntity<UserProfileResponse> getProfile(){
        return ResponseEntity.ok(getProfile.profile(currentEmail()));

    }
    @PutMapping("profile")
    public ResponseEntity<UserProfileResponse> updateProfile(@Valid @RequestBody UserUpdateReqest request){
        return ResponseEntity.ok(updateProfile.updateProfile(currentEmail(),request));
    }
    @PutMapping("change-password")
    public ResponseEntity<Void> changePassword( @Valid @RequestBody ChangePasswordRequest request){
        changePassword.changePassword(currentEmail(),request);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteProfile(){
        deleteAccount.deleteProfile(currentEmail());
        return ResponseEntity.ok().build();
    }
    private String currentEmail(){
        return securityUtil.getCurrentUserEmail();
    }
}
