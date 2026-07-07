package com.phanith.loginapp.domain;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
