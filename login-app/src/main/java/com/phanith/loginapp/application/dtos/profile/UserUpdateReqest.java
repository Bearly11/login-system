package com.phanith.loginapp.application.dtos.profile;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
public class UserUpdateReqest {
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First Name must contain letters and spaces only")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last Name must contain letters and spaces only")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Gender must contain letters only")
    private String gender;
    
}
