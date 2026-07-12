package com.phanith.loginapp.application.dtos.profile;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    
}
