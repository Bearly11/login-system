package com.phanith.loginapp.domain;

import com.phanith.loginapp.domain.enums.Type;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Token {
    private UUID id;
    private String token;
    private Type type;
    private LocalDate createAt;
    private LocalDate updateAt;

}
