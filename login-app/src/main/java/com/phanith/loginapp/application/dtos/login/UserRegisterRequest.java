package com.phanith.loginapp.application.dtos.login;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRegisterRequest {
    @NotBlank(message = "First name is required")
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "First Name must contain letters and spaces only"
    )
    private String firstName;
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Last Name must contain letters and spaces only"
    )
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Gender must contain letters only"
    )
    private String gender;
    @NotBlank(message = "Email is required")
    @Email(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Please provide a valid email address"
    )
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8,message = "Password must be least more then 8 character")
    private String password;
}
