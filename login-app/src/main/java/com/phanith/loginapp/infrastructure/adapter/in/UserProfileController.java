package com.phanith.loginapp.infrastructure.adapter.in;

import com.phanith.loginapp.application.dtos.profile.ChangePasswordRequest;
import com.phanith.loginapp.application.dtos.profile.UserProfileResponse;
import com.phanith.loginapp.application.dtos.profile.UserUpdateReqest;
import com.phanith.loginapp.application.port.in.profile.ChangePassword;
import com.phanith.loginapp.application.port.in.profile.DeleteAccount;
import com.phanith.loginapp.application.port.in.profile.GetProfile;
import com.phanith.loginapp.application.port.in.profile.UpdateProfile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile/")
@RequiredArgsConstructor
public class UserProfileController {
    private final GetProfile getProfile;
    private final UpdateProfile updateProfile;
    private final ChangePassword changePassword;
    private final DeleteAccount deleteAccount;

    @GetMapping("profile")
    public ResponseEntity<UserProfileResponse> getProfile(@AuthenticationPrincipal String email){
        return ResponseEntity.ok(getProfile.profile(email));

    }
    @PutMapping("profile")
    public ResponseEntity<UserProfileResponse> updateProfile(@AuthenticationPrincipal String email,
                                                             @Valid @RequestBody UserUpdateReqest request){
        return ResponseEntity.ok(updateProfile.updateProfile(email,request));
    }
    @PutMapping("change-password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal String email, @Valid @RequestBody ChangePasswordRequest request){
        changePassword.changePassword(email,request);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteProfile(@AuthenticationPrincipal String email){
        deleteAccount.deleteProfile(email);
        return ResponseEntity.ok().build();
    }
}
