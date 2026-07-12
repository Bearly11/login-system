package com.phanith.loginapp.application.dtos.profile;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class ChangePasswordRequest {
    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "New password must be at least 8 characters")
    private String newPassword;
    
}
